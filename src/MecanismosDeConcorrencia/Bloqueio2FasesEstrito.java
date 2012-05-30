package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
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
	public ArrayList<Operacao> executar(){

		int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		int quantidadeOperacoesRestantes = quantidadeTotalOperacoes(this.listaTransacoesRecebida);
		int posicaoTransacaoLista=0;   // Para saber qual transacao est� a ser executada no momento
		ArrayList<Integer> posicaoOperacaoTransacao = criarPosicoesOperacao(this.listaTransacoesRecebida); // Para saber qual operacao ser� executada dentro da lista de operacoes de uma transacao, a posicao no array � equivalente a posicao da transacao na lista de transacoes.
		boolean conseguiuExecutar = false;
		
		
		if(tipoDeLock.equals("lock")){
			if(tipoTratamentoDeadlock.equals(null)){
				
				//Polling criado para ficar alternando entre as transacoes e realizando( se poss�vel ) uma operacao de cada transacao por vez.
				while(!this.listaTransacoesRecebida.isEmpty()){					
					
					 						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que est� na vez.
						Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(0);//Pega sempre a primeira posicao da lista de operacoes
						
						
						if(!operacaoTemp.getNomeOperacao().equals("Commit") && !operacaoTemp.getNomeOperacao().equals("Begin")){
							
							
							conseguiuExecutar = executarOperacao(transacaoTemp, operacaoTemp); // Tenta executar a operacao chamando o lockBinario.
														
							//Se foi possivel de executar
							if(conseguiuExecutar){
								
								listaOperacoesFinal.add(operacaoTemp);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
								this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp);;//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
																														
								
							}	
							
							
						}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona begin a lista final
							this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
													
						
						// Ou seja, � Commit
						}else{
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona commit a lista final
							this.listaTransacoesRecebida.remove(transacaoTemp); // N�o precisa remover a operacao da lista de operacoes pois ser� mandado remover a transacao toda.
							desbloquearTudo(transacaoTemp); // M�todo a ser criado no lock, desbloqueia todas as vari�veis bloqueadas por determinada transacao
							
						}		
						
						//Polling pra passar de uma transacao para outra
						if(operacaoTemp.getNomeOperacao().equals("Commit")){
							
							if(posicaoTransacaoLista == (listaTransacoesRecebida.size())){
								posicaoTransacaoLista = 0;
							}// N�o tem else pq a posicao tem que permanecer a mesma, afinal ap�s remover um bojeto de uma lista, o seguinte ocupa seu lugar.
							
						}else{
							if(posicaoTransacaoLista == (listaTransacoesRecebida.size()-1)){
								posicaoTransacaoLista = 0; //Caso seja a ultima transacao, retorna para a primeira.
							}else{
								posicaoTransacaoLista++;
							}
							
						}					
				
				}
				
				
			}else if(tipoTratamentoDeadlock.equals("waitdie")){
				
			}else if(tipoTratamentoDeadlock.equals("woundwait")){
				
			}
			
		}else if(tipoDeLock.equals("lockMultiplo")){
			if(tipoTratamentoDeadlock.equals(null)){
				
				//Polling criado para ficar alternando entre as transacoes e realizando( se poss�vel ) uma operacao de cada transacao por vez.
				while(posicaoTransacaoLista <= quantidadeTransacoes){
										
					// Porque se fossem iguais iria tentar acessar uma posicao no array que n�o existe.
					if(posicaoTransacaoLista < quantidadeTransacoes){
						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que est� na vez.
						Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(posicaoOperacaoTransacao.get(posicaoTransacaoLista));
						
						
						ArrayList<Operacao> listaOperacoesAuxiliar = new ArrayList<Operacao>(); // Utilizada para auxiliar na verifica��o das opera��es restantes, pra ser passado como parametro e verificar se existe algo que necessite de um bloqueio.
						listaOperacoesAuxiliar = transacaoTemp.getListaOperacoes();
						
						
					
					
					}					
					
					if(quantidadeOperacoesRestantes == 0){
						posicaoTransacaoLista = (this.listaTransacoesRecebida.size() + 1); //Para encerrar o while e consequentemente o m�todo.
					}else if (posicaoTransacaoLista < quantidadeTransacoes){
						posicaoTransacaoLista++; // Para executar a opera��o da pr�xima transa��o.
					}else{
						posicaoTransacaoLista = 0; // Para depois de executar a operacao da ultima transacao retornar para a primeira.
					}
				}
				
			}else if(tipoTratamentoDeadlock.equals("waitdie")){
				
			}else if(tipoTratamentoDeadlock.equals("woundwait")){
				
			}
		}
		return listaOperacoesFinal;
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
	
	//Retorna uma lista de inteiros que ser� utilizada para sabermos o indice atual de uma lista de operacoes.
	//Recebe como par�metro a lista de transacoes apenas para saber quantos �ndices ser�o necess�rios, afinal a quantidade
	//de transacoes � a mesma quantidade de listas de operacoes existentes.
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
