package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;

public class Bloqueio2FasesEstrito {

	

	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<String> listaOperacoesFinal;
	
		
	/**
	 * Parametros utilizados para dizer qual associacao sera feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2FasesEstrito(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadLock;
		this.listaOperacoesFinal = new ArrayList<String>();
		
	}	
	
	/**
	 * Caracterizado por liberar os bloqueios "exclusivos" apenas apos um commit ou abort.
	 * **/
	public ArrayList<String> executar(Repositorio repositorio){

		int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		int posicaoTransacaoLista=0;   // Para saber qual transacao est‡ a ser executada no momento
		boolean conseguiuExecutar = false;
		ArrayList<Transacao> listaTransacaoAuxiliar = new ArrayList<Transacao>(quantidadeTransacoes); // Para auxiliar na verifica‹o de bloqueios futuros
		
		//Polling criado para ficar alternando entre as transacoes e realizando( se poss’vel ) uma operacao de cada transacao por vez.
		while(!this.listaTransacoesRecebida.isEmpty()){					
			
			 						
				Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que est‡ na vez.
				Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(0);//Pega sempre a primeira posicao da lista de operacoes
				
				
				if(!operacaoTemp.getNomeOperacao().equals("Commit") && !operacaoTemp.getNomeOperacao().equals("Begin")){
					
					
					conseguiuExecutar = executarOperacao(transacaoTemp, operacaoTemp); // Tenta executar a operacao chamando o lockBinario.
												
					//Se foi possivel de executar
					if(conseguiuExecutar){
						
						RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(), repositorio);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
						this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(0);//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
						listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().add(operacaoTemp); // usada para se fazer comparacoes, afinal ela vai conter as operacoes que ja foram executadas
						
						if(!operacaoTemp.getNomeOperacao().equals("Write")){
						
							//Se n‹o houver write ou read de alguma vari‡vel que n‹o foi bloqueada ainda || se n‹o houver passagem de read para write de alguam vari‡vel qualquer. || Se houver algum read dessa variavel em questao que esta dando read agora
							if(!existeBloqueiosFuturos(listaTransacaoAuxiliar, posicaoTransacaoLista, operacaoTemp)){
								
								executarUnlock(transacaoTemp, operacaoTemp);
								
							}
						
						
						}
						
						
						
					// Aqui entra o wait-die	
					}else{
						
						// Ou seja, a operacao da transacao n‹o foi executada e ela ainda por cima Ž mais nova que a transacao que lhe bloqueou.
						if(transacaoTemp.getTempoDeCria‹o() > tempoDaTransacaoBloqueada){
							
						}
						
					}
					
					
				}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
					RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(),repositorio); // Adiciona begin a lista final
					this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
											
				
				// Ou seja, Ž Commit
				}else{
					RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(), repositorio); // Adiciona commit a lista final
					this.listaTransacoesRecebida.remove(transacaoTemp); // N‹o precisa remover a operacao da lista de operacoes pois ser‡ mandado remover a transacao toda.
					desbloquearTudo(transacaoTemp); // MŽtodo a ser criado no lock, desbloqueia todas as vari‡veis bloqueadas por determinada transacao
					
				}		
				
				
				//Polling pra passar de uma transacao para outra
				if(operacaoTemp.getNomeOperacao().equals("Commit")){
					
					if(posicaoTransacaoLista == (listaTransacoesRecebida.size())){
						posicaoTransacaoLista = 0;
					}// N‹o tem else pq a posicao tem que permanecer a mesma, afinal ap—s remover um bojeto de uma lista, o seguinte ocupa seu lugar.
					
				}else{
					if(posicaoTransacaoLista == (listaTransacoesRecebida.size()-1)){
						posicaoTransacaoLista = 0; //Caso seja a ultima transacao, retorna para a primeira.
					}else{
						posicaoTransacaoLista++;
					}
					
				}					
		
		}
		
		return this.listaOperacoesFinal;
	}
	
	public void RetornoOperacaoString(Operacao o, String nomeTransacao, Repositorio rep) {
		String retorno= "";
		int j = 0;		
		int posicaoVariavel = 0;
		
		for(j = 0 ; j < rep.getListaVariaveis().size();j++){
			 if(rep.getListaVariaveis().get(j).getNomeVariavel().equals(o.getVariavel().getNomeVariavel())){
				  posicaoVariavel = j;
				  j = rep.getListaVariaveis().size();
			 }
		}
		
		// colocando a variavel a ser modificada na lista de variaveis antigas e substitui
		//o valor antigo pelo novo na lista de variaveis atuais
		if(o.getNomeOperacao().equals("Write")){
			rep.ValoresAntigosVariaveis(rep.getListaVariaveis().get(posicaoVariavel));
			rep.getListaVariaveis().get(posicaoVariavel).setValor(""+o.getValorNovo());
		}
		
		retorno =  nomeTransacao+" "+o.getNomeOperacao()+"lock "+o.getVariavel().getNomeVariavel()+"\n"+nomeTransacao+" "+o.getNomeOperacao()+"_item "+o.getVariavel().getNomeVariavel()+" "+o.getValorAntigo()+" "+o.getValorNovo();  
		this.listaOperacoesFinal.add(retorno);
		
	}

	private void desbloquearTudo(Transacao transacaoTemp) {
		// TODO Auto-generated method stub
		
	}
	
	// Verifica se existir‡ algum bloqueio que impede o unlock da opera‹o que est‡ dando Read no momento atual na vari‡vel em quest‹o.
	public boolean existeBloqueiosFuturos(ArrayList<Transacao> listaTransacaoAuxiliar, int posicaoTransacaoLista, Operacao operacaoTemp) {
		
		boolean existeBloqueioFuturo = false;		
		ArrayList<Operacao> listaOperacoesRecebidaTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(); //Operacoes Futuras
		
		for(int i=0; i < listaOperacoesRecebidaTemp.size(); i++){
			
			for(int j=0; j < listaTransacaoAuxiliar.size(); j++){
				
				// Se houver ainda write ou read de alguma vari‡vel que n‹o foi bloqueada ainda 
				if(!listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().get(j).getVariavel().getNomeVariavel().equals(listaOperacoesRecebidaTemp.get(i).getVariavel().getNomeVariavel())){
					existeBloqueioFuturo = true;
				}else{
					//Se houver write de alguma vari‡vel que s— deu read(ou seja, ainda ter‡ uma promo‹o)
					if(listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().get(j).getNomeOperacao().equals("Read") && listaOperacoesRecebidaTemp.get(i).getNomeOperacao().equals("Write")){
						existeBloqueioFuturo = true;
					}
				}
				
			}
						
		}
		
		// Se houver algum read dessa variavel em questao que esta dando read agora
		for(int z=0; z < listaOperacoesRecebidaTemp.size(); z++){
			
			if(listaOperacoesRecebidaTemp.get(z).getVariavel().getNomeVariavel().equals(operacaoTemp.getVariavel().getNomeVariavel())){
				
				if(listaOperacoesRecebidaTemp.get(z).getNomeOperacao().equals("Read")){
					existeBloqueioFuturo = true;
				}
				
			}
			
		}
		
		return existeBloqueioFuturo;
	}
	
	public boolean executarOperacao(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void executarUnlock(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		
	}

}
