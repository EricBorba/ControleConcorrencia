package MecanismosDeConcorrencia;

import java.util.ArrayList;
import GerenciaDeDados.Transacao;

public class Bloqueio2Fases {
		
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Transacao> listaTransacoesAlterada;
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2Fases(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadock;
	}
	
	/**
	 * Caracterizado por realizar os bloqueios das vari‡veis aos poucos ˆ medida que vai 
	 * solicitando o acesso as vari‡veis; no entanto s— pode desbloquear uma vari‡vel
	 * ap—s ter a certeza que n‹o vai precisar realizar mais nenhum Block ( de qualquer
	 * vari‡vel), pois depois realizar um desbloqueio n‹o poder‡ realizar mais nenhum
	 * bloqueio.
	 * **/
	public void Bloqueio2FasesBasico(){
		
	}
	
	
	/**
	 * Bloqueia todos as vari‡veis que ir‡ utilizar e vai liberando os bloqueios "esclusivos" aos poucos.
	 * **/
	public void Bloqueio2FasesBasicoConservador(){
		
	}
	
	/**
	 * Caracterizado por liberar os bloqueios "exclusivos" apenas ap—s um commit ou abort.
	 * **/
	public void Bloqueio2FasesEstrito(){
		
	}
	
}
