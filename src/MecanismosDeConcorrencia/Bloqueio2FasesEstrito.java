package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;

public class Bloqueio2FasesEstrito {

	

	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Operacao> listaOperacoesFinal;
		
	/**
	 * Parametros utilizados para dizer qual associacao sera feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2FasesEstrito(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadLock;
		this.listaOperacoesFinal = new ArrayList<Operacao>();
	}	
	
	/**
	 * Caracterizado por liberar os bloqueios "exclusivos" apenas apos um commit ou abort.
	 * **/
	public ArrayList<Operacao> executar(Repositorio repositorio){

		int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		int posicaoTransacaoLista=0;   // Para saber qual transacao está a ser executada no momento
		boolean conseguiuExecutar = false;
		ArrayList<Transacao> listaTransacaoAuxiliar = new ArrayList<Transacao>(quantidadeTransacoes); // Para auxiliar na verificação de bloqueios futuros
		
		//Polling criado para ficar alternando entre as transacoes e realizando( se possível ) uma operacao de cada transacao por vez.
		while(!this.listaTransacoesRecebida.isEmpty()){					
			
			 						
				Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que está na vez.
				Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(0);//Pega sempre a primeira posicao da lista de operacoes
				
				
				if(!operacaoTemp.getNomeOperacao().equals("Commit") && !operacaoTemp.getNomeOperacao().equals("Begin")){
					
					
					conseguiuExecutar = executarOperacao(transacaoTemp, operacaoTemp); // Tenta executar a operacao chamando o lockBinario.
												
					//Se foi possivel de executar
					if(conseguiuExecutar){
						
						RetornoOperaçãoString(operacaoTemp,transacaoTemp.getnomeTransacao(), this.listaOperacoesFinal, repositorio);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
						this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(0);//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
						listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().add(operacaoTemp); // usada para se fazer comparacoes, afinal ela vai conter as operacoes que ja foram executadas
						
						if(!operacaoTemp.getNomeOperacao().equals("Write")){
						
							//Se houver ainda write de alguma variável que não foi bloqueada ainda e n tem read dessa variavel tb || se houver read de alguma variável que não foi bloqueada ainda
							if(!existeBloqueiosFuturos(listaTransacaoAuxiliar, posicaoTransacaoLista)){
								
								executarUnlock(transacaoTemp, operacaoTemp);
							}
						
						
						}
						
						
						
					// Aqui entra o wait-die	
					}else{
						
					}
					
					
				}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
					RetornoOperaçãoString(operacaoTemp,transacaoTemp.getnomeTransacao(), this.listaOperacoesFinal, repositorio); // Adiciona begin a lista final
					this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
											
				
				// Ou seja, é Commit
				}else{
					RetornoOperaçãoString(operacaoTemp,transacaoTemp.getnomeTransacao(), this.listaOperacoesFinal, repositorio); // Adiciona commit a lista final
					this.listaTransacoesRecebida.remove(transacaoTemp); // Não precisa remover a operacao da lista de operacoes pois será mandado remover a transacao toda.
					desbloquearTudo(transacaoTemp); // Método a ser criado no lock, desbloqueia todas as variáveis bloqueadas por determinada transacao
					
				}		
				
				
				//Polling pra passar de uma transacao para outra
				if(operacaoTemp.getNomeOperacao().equals("Commit")){
					
					if(posicaoTransacaoLista == (listaTransacoesRecebida.size())){
						posicaoTransacaoLista = 0;
					}// Não tem else pq a posicao tem que permanecer a mesma, afinal após remover um bojeto de uma lista, o seguinte ocupa seu lugar.
					
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
	
	public void RetornoOperaçãoString(Operacao operacaoTemp,
			String getnomeTransacao, ArrayList<Operacao> listaOperacoesFinal2,
			Repositorio repositorio) {
		// TODO Auto-generated method stub
		
	}

	private void desbloquearTudo(Transacao transacaoTemp) {
		// TODO Auto-generated method stub
		
	}
	
	//Se houver ainda writ ou read de alguma variável que não foi bloqueada ainda || se houver write de alguma variável que só deu read(ou seja, ainda terá uma promoção)
	public boolean existeBloqueiosFuturos(ArrayList<Transacao> listaTransacaoAuxiliar, int posicaoTransacaoLista) {
		
		boolean existeBloqueioFuturo = false;		
		ArrayList<Operacao> listaOperacoesRecebidaTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(); //Operacoes Futuras
		
		for(int i=0; i < listaOperacoesRecebidaTemp.size(); i++){
			
			for(int j=0; j < listaTransacaoAuxiliar.size(); j++){
				
				if(!listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().get(j).getVariavel().getNomeVariavel().equals(listaOperacoesRecebidaTemp.get(i).getVariavel().getNomeVariavel())){
					existeBloqueioFuturo = true;
				}else{
					if(listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().get(j).getNomeOperacao().equals("Read") && listaOperacoesRecebidaTemp.get(i).getNomeOperacao().equals("Write")){
						existeBloqueioFuturo = true;
					}
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
