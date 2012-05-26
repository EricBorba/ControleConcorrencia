

package MecanismosDeConcorrencia;

import java.util.ArrayList;
import GerenciaDeDados.Transacao;

/**
 * Classe criada para realizar o bloqueio de 2 fases fazendo uso tamb�m das classes de lock e lock m�ltiplo bem como o tratamento
 * de deadlock wait-die e wound-wait...
 * A classe recebe como par�metro a lista das transa��es j� criadas bem como o tipo de lock e tratamento de deadlock que devem ser realizados.
 * **/


/**
 * @author ERB & FAD
 *
 */

public class Bloqueio2Fases {
		
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Transacao> listaTransacoesAlterada;
	
	/**
	 * Par�metros utilizados para dizer qual associa��o ser� feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2Fases(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadLock;
	}
	
	/**
	 * Caracterizado por realizar os bloqueios das vari�veis aos poucos � medida que vai 
	 * solicitando o acesso as vari�veis; no entanto s� pode desbloquear uma vari�vel
	 * ap�s ter a certeza que n�o vai precisar realizar mais nenhum Block ( de qualquer
	 * vari�vel), pois depois realizar um desbloqueio n�o poder� realizar mais nenhum
	 * bloqueio.
	 * **/
	public void Bloqueio2FasesBasico(){
		
	}
	
	
	/**
	 * Bloqueia todos as vari�veis que ir� utilizar e vai liberando os bloqueios "esclusivos" aos poucos.
	 * **/
	public void Bloqueio2FasesBasicoConservador(){
		
	}
	
	/**
	 * Caracterizado por liberar os bloqueios "exclusivos" apenas ap�s um commit ou abort.
	 * **/
	public void Bloqueio2FasesEstrito(){
		
	}
	
}
