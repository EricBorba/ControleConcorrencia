package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Transacao;


public class Bloqueio2FasesConservador {

	
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Operacao> listaOperacoesFinal;
		
	/**
	 * Parametros utilizados para dizer qual associacao sera feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2FasesConservador(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadLock;
		this.listaOperacoesFinal = new ArrayList<Operacao>();
	}	
	
	
	/**
	 * Bloqueia todos as variaveis que ira utilizar e vai liberando os bloqueios "exclusivos" aos poucos.
	 * **/
	public ArrayList<Operacao> executar(){

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
	
	
}
