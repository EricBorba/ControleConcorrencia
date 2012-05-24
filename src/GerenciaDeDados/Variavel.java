package GerenciaDeDados;
/** 
 * Estrutura primitiva para ser poss’vel guardar valores que ser‹o mais tarde lidos, alterados ou removidos.
 */

/**
 * @author ERB & FAD
 *
 */
public class Variavel {

	String nomeVariavel;
	String valorVariavel;
	
	public Variavel(String variavel, String valor){
		
		this.nomeVariavel = variavel;
		this.valorVariavel = valor;		
	}

	public String getVariavel() {
		return nomeVariavel;
	}

	public String getValor() {
		return valorVariavel;
	}

	public void setVariavel(String variavel) {
		this.nomeVariavel = variavel;
	}

	public void setValor(String valor) {
		this.valorVariavel = valor;
	}


}
