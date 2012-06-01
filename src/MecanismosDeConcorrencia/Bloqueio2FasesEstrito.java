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
		int quantidadeOperacoesRestantes = quantidadeTotalOperacoes(this.listaTransacoesRecebida);
		int posicaoTransacaoLista=0;   // Para saber qual transacao est‡ a ser executada no momento
		ArrayList<Integer> posicaoOperacaoTransacao = criarPosicoesOperacao(this.listaTransacoesRecebida); // Para saber qual operacao ser‡ executada dentro da lista de operacoes de uma transacao, a posicao no array Ž equivalente a posicao da transacao na lista de transacoes.
		boolean conseguiuExecutar = false;
		
		//Polling criado para ficar alternando entre as transacoes e realizando( se poss’vel ) uma operacao de cada transacao por vez.
		while(!this.listaTransacoesRecebida.isEmpty()){					
			
			 						
				Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que est‡ na vez.
				Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(0);//Pega sempre a primeira posicao da lista de operacoes
				
				
				if(!operacaoTemp.getNomeOperacao().equals("Commit") && !operacaoTemp.getNomeOperacao().equals("Begin")){
					
					
					conseguiuExecutar = executarOperacao(transacaoTemp, operacaoTemp); // Tenta executar a operacao chamando o lockBinario.
												
					//Se foi possivel de executar
					if(conseguiuExecutar){
						
						if(!operacaoTemp.getNomeOperacao().equals("Write")){
						
							if(!condicao){
								//Se houver write de alguma vari‡vel que n‹o deu read ainda || se houver read de alguma vari‡vel que n‹o deu read ainda
								executarUnlock(transacaoTemp, operacaoTemp);
							}
						
						
						}
						
						RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(), this.listaOperacoesFinal, repositorio);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
						this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp);;//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
						
					// Aqui entra o wait-die	
					}else{
						
					}
					
					
				}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
					RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(), this.listaOperacoesFinal, repositorio); // Adiciona begin a lista final
					this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
											
				
				// Ou seja,  Commit
				}else{
					RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(), this.listaOperacoesFinal, repositorio); // Adiciona commit a lista final
					this.listaTransacoesRecebida.remove(transacaoTemp); // Nao precisa remover a operacao da lista de operacoes pois ser‡ mandado remover a transacao toda.
					desbloquearTudo(transacaoTemp); // Metodo a ser criado no lock, desbloqueia todas as vari‡veis bloqueadas por determinada transacao
					
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
	/**modifica a variavel e escreve na lista de log*/
	public void RetornoOperacaoString(Operacao o,String nomeTransacao, ArrayList<String> listaOperacoesFinal2,Repositorio rep){
		String retorno= "";
		int j = 0;
		int posicaoVariavel = 0;
		
		for(j = 0 ; j < rep.getListaVariaveis().size();j++){
			
			if(rep.getListaVariaveis().get(j).getVariavel().equals(o.getVariavel().getVariavel())){
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
		
		retorno =  nomeTransacao+" "+o.getNomeOperacao()+"lock "+o.getVariavel().getVariavel()+"\n"+nomeTransacao+" "+o.getNomeOperacao()+"_item "+o.getVariavel().getVariavel()+" "+o.getValorAntigo()+" "+o.getValorNovo();	
		listaOperacoesFinal2.add(retorno);
		
				
	}

	private void desbloquearTudo(Transacao transacaoTemp) {
		// TODO Auto-generated method stub
		
	}

	private boolean existeBloqueiosFuturosVariavelEspecifica(
			ArrayList<Operacao> listaOperacoesAuxiliar) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean existeBloqueiosFuturos(ArrayList<Operacao> listaOperacoes) {
		boolean existeBloqueioFuturo = false;
		for(int i=0; i<listaOperacoes.size();i++){
			
			if(listaOperacoes.get(i).getNomeOperacao().equals("Read") || listaOperacoes.get(i).getNomeOperacao().equals("Write")){
				existeBloqueioFuturo = true;
			}
		}
		
		return existeBloqueioFuturo;
	}

	//Retorna a quantidade de operacoes totais a serem realizadas por todas as transacoes
	private int quantidadeTotalOperacoes(ArrayList<Transacao> listaTransacoesRecebida2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Retorna uma lista de inteiros que ser‡ utilizada para sabermos o indice atual de uma lista de operacoes.
	//Recebe como par‰metro a lista de transacoes apenas para saber quantos ’ndices ser‹o necess‡rios, afinal a quantidade
	//de transacoes Ž a mesma quantidade de listas de operacoes existentes.
	private ArrayList<Integer> criarPosicoesOperacao(ArrayList<Transacao> listaTransacoesRecebida2) {
		ArrayList<Integer> listaIndicesOperacao = new ArrayList<Integer>();
		
		for(int i=0; i < listaTransacoesRecebida2.size(); i++){
			listaIndicesOperacao.add(i, 0);			
		}
		
		return listaIndicesOperacao;
	}
	
	private boolean executarOperacao(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void executarUnlock(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		
	}
}
