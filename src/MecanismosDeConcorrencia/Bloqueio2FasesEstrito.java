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
		int posicaoTransacaoLista=0;   // Para saber qual transacao está a ser executada no momento
		ArrayList<Integer> posicaoOperacaoTransacao = criarPosicoesOperacao(this.listaTransacoesRecebida); // Para saber qual operacao será executada dentro da lista de operacoes de uma transacao, a posicao no array é equivalente a posicao da transacao na lista de transacoes.
		boolean conseguiuExecutar = false;
		
		
		if(tipoDeLock.equals("lock")){
			if(tipoTratamentoDeadlock.equals(null)){
				
				//Polling criado para ficar alternando entre as transacoes e realizando( se possível ) uma operacao de cada transacao por vez.
				while(!this.listaTransacoesRecebida.isEmpty()){					
					
					 						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que está na vez.
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
													
						
						// Ou seja, é Commit
						}else{
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona commit a lista final
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
				
				
			}else if(tipoTratamentoDeadlock.equals("waitdie")){
				
			}else if(tipoTratamentoDeadlock.equals("woundwait")){
				
			}
			
		}else if(tipoDeLock.equals("lockMultiplo")){
			if(tipoTratamentoDeadlock.equals(null)){
				
				//Polling criado para ficar alternando entre as transacoes e realizando( se possível ) uma operacao de cada transacao por vez.
				while(posicaoTransacaoLista <= quantidadeTransacoes){
										
					// Porque se fossem iguais iria tentar acessar uma posicao no array que não existe.
					if(posicaoTransacaoLista < quantidadeTransacoes){
						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que está na vez.
						Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(posicaoOperacaoTransacao.get(posicaoTransacaoLista));
						
						
						ArrayList<Operacao> listaOperacoesAuxiliar = new ArrayList<Operacao>(); // Utilizada para auxiliar na verificação das operações restantes, pra ser passado como parametro e verificar se existe algo que necessite de um bloqueio.
						listaOperacoesAuxiliar = transacaoTemp.getListaOperacoes();
						
						
					
					
					}					
					
					if(quantidadeOperacoesRestantes == 0){
						posicaoTransacaoLista = (this.listaTransacoesRecebida.size() + 1); //Para encerrar o while e consequentemente o método.
					}else if (posicaoTransacaoLista < quantidadeTransacoes){
						posicaoTransacaoLista++; // Para executar a operação da próxima transação.
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
	
	//Retorna uma lista de inteiros que será utilizada para sabermos o indice atual de uma lista de operacoes.
	//Recebe como parâmetro a lista de transacoes apenas para saber quantos índices serão necessários, afinal a quantidade
	//de transacoes é a mesma quantidade de listas de operacoes existentes.
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
