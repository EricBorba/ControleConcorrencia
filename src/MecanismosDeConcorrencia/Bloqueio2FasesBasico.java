

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
		//ArrayList<Integer> posicaoOperacaoTransacao = criarPosicoesOperacao(this.listaTransacoesRecebida); // Para saber qual operacao será executada dentro da lista de operacoes de uma transacao, a posicao no array é equivalente a posicao da transacao na lista de transacoes.
		int posicaoTransacaoLista=0;   // Para saber qual transacao está a ser executada no momento
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
								
								
								if((!existeBloqueiosFuturos(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes())) && (!existeLeiturasFuturasVariavelEspecifica(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(), operacaoTemp)) ){
									
									executarUnlock(transacaoTemp, operacaoTemp); //Se não existe bloqueios futuros é passado como parâmetro a mesma operação que foi executada, poisela contém a variável que já pode ser desbloqueada.
									
								}							
								
							}	
							
							
						}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona begin a lista final
							this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
													
						
						// Ou seja, é Commit
						}else{
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona commit a lista final
							this.listaTransacoesRecebida.remove(transacaoTemp); // Não precisa remover a operacao da lista de operacoes pois será mandado remover a transacao toda.
							
							
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
					
				//Polling criado para ser possível alternar entre as transacoes e realizando( se possível ) uma operacao de cada transacao por vez. 
				while(!this.listaTransacoesRecebida.isEmpty()){					
					
					 						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que está na vez.
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
											
										
									}else{ // Ou seja, a operacao atual é Read
										
										if(!existeReadFuturo(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(), operacaoTemp)){
											executarUnlock(transacaoTemp, operacaoTemp);
										}
										
									}												
									
								}												
								
							}	
							
							
						}else if(operacaoTemp.getNomeOperacao().equals("Begin")){
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona begin a lista final
							this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp); // remove a operacao da lista de operacoes.
													
						
						// Ou seja, é Commit
						}else{
							this.listaOperacoesFinal.add(operacaoTemp); // Adiciona commit a lista final
							this.listaTransacoesRecebida.remove(transacaoTemp); // Não precisa remover a operacao da lista de operacoes pois será mandado remover a transacao toda.
							
							
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

	// Passagem de read para write de qualquer variável, auxilia para verificar se terminou a fase de crescimento
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
	
	//Retorna uma lista de inteiros que será utilizada para sabermos o indice atual de uma lista de operacoes.
	//Recebe como parâmetro a lista de transacoes apenas para saber quantos índices serão necessários, afinal a quantidade
	//de transacoes é a mesma quantidade de listas de operacoes existentes.
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
