

package MecanismosDeConcorrencia;

import java.util.ArrayList;
import GerenciaDeDados.Transacao;

/**
 * Classe criada para realizar o bloqueio de 2 fases fazendo uso também das classes de lock e lock múltiplo bem como o tratamento
 * de deadlock wait-die e wound-wait...
 * A classe recebe como parâmetro a lista das transações já criadas bem como o tipo de lock e tratamento de deadlock que devem ser realizados.
 * **/


/**
 * @author ERB & FAD
 *
 */

public class Bloqueio2Fases {
		
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Transacao> listaTransacoesAlterada;
	
	/**
	 * Parâmetros utilizados para dizer qual associação será feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2Fases(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadLock;
	}
	
	/**
	 * Caracterizado por realizar os bloqueios das variáveis aos poucos à medida que vai 
	 * solicitando o acesso as variáveis; no entanto só pode desbloquear uma variável
	 * após ter a certeza que não vai precisar realizar mais nenhum Block ( de qualquer
	 * variável), pois depois realizar um desbloqueio não poderá realizar mais nenhum
	 * bloqueio.
	 * **/
	public void Bloqueio2FasesBasico(){
		
	}
	
	
	/**
	 * Bloqueia todos as variáveis que irá utilizar e vai liberando os bloqueios "esclusivos" aos poucos.
	 * **/
	public void Bloqueio2FasesBasicoConservador(){
		
	}
	
	/**
	 * Caracterizado por liberar os bloqueios "exclusivos" apenas após um commit ou abort.
	 * **/
	public void Bloqueio2FasesEstrito(){
		
	}
	
}
