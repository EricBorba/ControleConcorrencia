

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

public class Bloqueio2Fases {
		
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Operacao> listaOperacoesFinal;
		
	/**
	 * Parametros utilizados para dizer qual associacao sera feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2Fases(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
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
	public ArrayList<Operacao> Bloqueio2FasesBasico(){
		
		int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		int quantidadeOperacoesRestantes = quantidadeTotalOperacoes(this.listaTransacoesRecebida);
		int posicaoTransacaoLista=0;
		int posicaoOperacaoTransacao = 0;
		boolean conseguiuBloquear = false;
		boolean existeLock = false;
		
		
		if(tipoDeLock.equals("lock")){
			
			if(tipoTratamentoDeadlock.equals(null)){
				
						
				while(posicaoTransacaoLista < this.listaTransacoesRecebida.size()){
					
					Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista);
					Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(posicaoOperacaoTransacao);
					
					
					
					
					//Polling criado para ficar alternando entre as transacoes e realizando uma operacao de cada uma por vez.
					if(quantidadeOperacoesRestantes == 0){
						posicaoTransacaoLista = this.listaTransacoesRecebida.size(); //Para encerrar o while e consequentemente o mŽtodo.
					}else if (posicaoTransacaoLista <= quantidadeTransacoes){
						posicaoTransacaoLista++;
					}else{
						posicaoTransacaoLista = 0;
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
	
		
	/**
	 * Bloqueia todos as variaveis que ira utilizar e vai liberando os bloqueios "exclusivos" aos poucos.
	 * **/
	public ArrayList<Operacao> Bloqueio2FasesBasicoConservador(){

		if(tipoDeLock.equals("lock")){
			if(tipoTratamentoDeadlock.equals(null)){
				
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
	
	/**
	 * Caracterizado por liberar os bloqueios "exclusivos" apenas apos um commit ou abort.
	 * **/
	public ArrayList<Operacao> Bloqueio2FasesEstrito(){

		if(tipoDeLock.equals("lock")){
			if(tipoTratamentoDeadlock.equals(null)){
				
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
	
	// Verifica se ainda existe alguma operacao de write ou read dentro da lista de operacoes passada com parametro.
	public boolean existeBloqueiosFuturos(ArrayList<Operacao> listaOperacoes){
		
		boolean existeBloqueio = false;
		for(int i=0; i<listaOperacoes.size();i++){
			
			if(listaOperacoes.get(i).getNomeOperacao().equals("Read") || listaOperacoes.get(i).getNomeOperacao().equals("Write")){
				existeBloqueio = true;
			}
		}
		
		return existeBloqueio;
	}
	
	private int quantidadeTotalOperacoes(ArrayList<Transacao> listaTransacoesRecebida2) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
