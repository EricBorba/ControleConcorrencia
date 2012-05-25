package MecanismosDeConcorrencia;

import java.util.ArrayList;
import GerenciaDeDados.Transacao;

public class Bloqueio2Fases {
		
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Transacao> listaTransacoesAlterada;
	
	/**
	 * Parâmetros utilizados para dizer qual associação será feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2Fases(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadock;
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
