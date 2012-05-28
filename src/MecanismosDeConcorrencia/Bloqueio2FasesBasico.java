

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
		
		int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		int quantidadeOperacoesRestantes = quantidadeTotalOperacoes(this.listaTransacoesRecebida);
		int posicaoTransacaoLista=0;   // Para saber qual transacao está a ser executada no momento
		ArrayList<Integer> posicaoOperacaoTransacao = criarPosicoesOperacao(this.listaTransacoesRecebida); // Para saber qual operacao será executada dentro da lista de operacoes de uma transacao, a posicao no array é equivalente a posicao da transacao na lista de transacoes.
		boolean conseguiuExecutar = false;
		boolean jaDeuUnlock = false;
		
		
		if(tipoDeLock.equals("lock")){
			
			if(tipoTratamentoDeadlock.equals(null)){
				
				
				//Polling criado para ficar alternando entre as transacoes e realizando( se possível ) uma operacao de cada transacao por vez.
				while(posicaoTransacaoLista <= quantidadeTransacoes){
					
					
					// Porque se fossem iguais iria tentar acessar uma posicao no array que não existe.
					if(posicaoTransacaoLista < quantidadeTransacoes){
					 						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que está na vez.
						Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(posicaoOperacaoTransacao.get(posicaoTransacaoLista));
						
						
						ArrayList<Operacao> listaOperacoesAuxiliar = new ArrayList<Operacao>(); // Utilizada para auxiliar na verificação das operações restantes, pra ser passado como parametro e verificar se existe algo que necessite de um bloqueio.
						listaOperacoesAuxiliar = transacaoTemp.getListaOperacoes();
						
						
						conseguiuExecutar = executarOperacao(transacaoTemp, operacaoTemp); // Tenta executar a operacao
						
						
						//Se foi possivel de executar
						if(conseguiuExecutar){
							
							listaOperacoesAuxiliar.remove(operacaoTemp);//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
							listaOperacoesFinal.add(operacaoTemp);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
							posicaoOperacaoTransacao.set(posicaoTransacaoLista, (posicaoOperacaoTransacao.get(posicaoTransacaoLista)+1));//Passar para a proxima operacao dentro da lista de operacoes de uma tranasacao
							quantidadeOperacoesRestantes = quantidadeOperacoesRestantes - 1;
							
							if((!existeBloqueiosFuturos(listaOperacoesAuxiliar)) && !jaDeuUnlock ){
								executarUnlock(transacaoTemp, operacaoTemp); //Se não existe bloqueios futuros é passado como parâmetro a mesma operação que foi executada, poisela contém a variável que já pode ser desbloqueada.
								jaDeuUnlock = true;	
							}							
							
						}															
					
					}					
										
					if(quantidadeOperacoesRestantes == 0){
						posicaoTransacaoLista = (this.listaTransacoesRecebida.size() + 1); //Para encerrar o while e consequentemente o método.
					}else if (posicaoTransacaoLista < quantidadeTransacoes){
						posicaoTransacaoLista++; // Para executar a operação da próxima transação.
					}else{
						posicaoTransacaoLista = 0; // Para depois de executa a operacao da ultima transacao retornar para a primeira.
					}
				
				
				}
				
				
			}else if(tipoTratamentoDeadlock.equals("waitdie")){
				
			}else if(tipoTratamentoDeadlock.equals("woundwait")){
				
			}
			
		}else if(tipoDeLock.equals("lockMultiplo")){
			if(tipoTratamentoDeadlock.equals(null)){
				
			}else if(tipoTratamentoDeadlock.equals("waitdie")){
				
			}else if(tipoTratamentoDeadlock.equals("woundwait")){
				
			}
		}
		
		return listaOperacoesFinal;
		
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
