

package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Transacao;

/**
 * Classe criada para realizar o bloqueio de 2 fases fazendo uso tambem das classes de lock e lock multiplo bem como o tratamento
 * de deadlock wait-die e wound-wait...
 * A classe recebe como parametro a lista das transacoes ja criadas bem como o tipo de lock e tratamento de deadlock que devem ser realizados.
 * **/


/**
 * @author ERB & FAD
 *
 */

public class Bloqueio2FasesBasico {
		
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Operacao> listaOperacoesFinal;
		
	/**
	 * Parametros utilizados para dizer qual associacao sera feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2FasesBasico(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadLock;
		this.listaOperacoesFinal = new ArrayList<Operacao>();
	}
	
	/**
	 * Caracterizado por realizar os bloqueios das variaveis aos poucos a medida que vai 
	 * solicitando o acesso das mesmas; no entanto so pode desbloquear uma variavel
	 * apos ter a certeza que nao vai precisar realizar mais nenhum Block ( de qualquer
	 * variavel), pois depois de realizar um desbloqueio nao podera realizar mais nenhum
	 * bloqueio.
	 * **/
	public ArrayList<Operacao> executar(){
		
		//int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		//int quantidadeOperacoesRestantes = quantidadeTotalOperacoes(this.listaTransacoesRecebida);
		//ArrayList<Integer> posicaoOperacaoTransacao = criarPosicoesOperacao(this.listaTransacoesRecebida); // Para saber qual operacao ser� executada dentro da lista de operacoes de uma transacao, a posicao no array � equivalente a posicao da transacao na lista de transacoes.
		int posicaoTransacaoLista=0;   // Para saber qual transacao est� a ser executada no momento
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
								
								
								if((!existeBloqueiosFuturos(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes())) && (!existeLeiturasFuturasVariavelEspecifica(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(), operacaoTemp)) ){
									
									executarUnlock(transacaoTemp, operacaoTemp); //Se n�o existe bloqueios futuros � passado como par�metro a mesma opera��o que foi executada, poisela cont�m a vari�vel que j� pode ser desbloqueada.
									
								}							
								
							}	
							
							
						}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona begin a lista final
							this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
													
						
						// Ou seja, � Commit
						}else{
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona commit a lista final
							this.listaTransacoesRecebida.remove(transacaoTemp); // N�o precisa remover a operacao da lista de operacoes pois ser� mandado remover a transacao toda.
							
							
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
					
				//Polling criado para ser poss�vel alternar entre as transacoes e realizando( se poss�vel ) uma operacao de cada transacao por vez. 
				while(!this.listaTransacoesRecebida.isEmpty()){					
					
					 						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que est� na vez.
						Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(0); // Pega sempre a primeira operacao da lista de operacoes
						
						
						if(!operacaoTemp.getNomeOperacao().equals("Commit") && !operacaoTemp.getNomeOperacao().equals("Begin")){
							
							
							conseguiuExecutar = executarOperacao(transacaoTemp, operacaoTemp); // Tenta executar a operacao chamando o lockBinario.
														
							//Se foi possivel de executar
							if(conseguiuExecutar){
								
																
								listaOperacoesFinal.add(operacaoTemp);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
								this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp);;//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
								
								if((!existeBloqueiosFuturos(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes())) && (!existePassagemReadWrite(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes()))){
									
									if(operacaoTemp.getNomeOperacao().equals("Write")){
										
										if(existeLeiturasFuturasVariavelEspecifica(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(), operacaoTemp)){
											
											if(existeWriteFuturo(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(), operacaoTemp)){
												//faz nada pq ja adicionou na lista final e n pode relaxar
											}else{
												mandarRelaxar(transacaoTemp, operacaoTemp);//afinal n vai ter mais write
											}
											
										}else{
											executarUnlock(transacaoTemp, operacaoTemp);//n tem mais nem read_x, nem write_x
										}
											
										
									}else{ // Ou seja, a operacao atual � Read
										
										if(!existeReadFuturo(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(), operacaoTemp)){
											executarUnlock(transacaoTemp, operacaoTemp);
										}
										
									}												
									
								}												
								
							}	
							
							
						}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona begin a lista final
							this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
													
						
						// Ou seja, � Commit
						}else{
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona commit a lista final
							this.listaTransacoesRecebida.remove(transacaoTemp); // N�o precisa remover a operacao da lista de operacoes pois ser� mandado remover a transacao toda.
							
							
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
		}
		
		return listaOperacoesFinal;
		
	}	
	
	private boolean existeReadFuturo(ArrayList<Operacao> listaOperacoes,
			Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}

	private void mandarRelaxar(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		
	}

	private boolean existeWriteFuturo(ArrayList<Operacao> listaOperacoes,
			Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean existeOperacaoIgualASerFeita(ArrayList<Operacao> listaOperacoes,
			Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}

	// Passagem de read para write de qualquer vari�vel, auxilia para verificar se terminou a fase de crescimento
	private boolean existePassagemReadWrite(ArrayList<Operacao> listaOperacoes) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean existeLeiturasFuturasVariavelEspecifica(
			ArrayList<Operacao> listaOperacoesAuxiliar, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}

	// Verifica se ainda existe alguma operacao de write ou read dentro da lista de operacoes passada como parametro.
	public boolean existeBloqueiosFuturos(ArrayList<Operacao> listaOperacoes){
		
		boolean existeBloqueioFuturo = false;
		for(int i=0; i<listaOperacoes.size();i++){
			
			if(listaOperacoes.get(i).getNomeOperacao().equals("Read") || listaOperacoes.get(i).getNomeOperacao().equals("Write")){
				existeBloqueioFuturo = true;
			}
		}
		
		return existeBloqueioFuturo;
	}
	
	/**
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
	}**/
	
	private boolean executarOperacao(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void executarUnlock(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		
	}

	
}
