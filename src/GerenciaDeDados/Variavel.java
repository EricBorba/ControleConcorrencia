package GerenciaDeDados;
/** 
 * Estrutura primitiva para ser poss�vel guardar valores que ser�o mais tarde lidos, alterados ou removidos.
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

	public String getNomeVariavel() {
		return nomeVariavel;
	}

	public String getValor() {
		return valorVariavel;
	}

	public void setNomeVariavel(String variavel) {
		this.nomeVariavel = variavel;
	}

	public void setValor(String valor) {
		this.valorVariavel = valor;
	}
	
	/**M�todo criado para auxiliar na busca por uma variavel quando ela estiver dentro
	 * de uma lista, e consequentemente na remo��o da mesma dessa lista**/
	public boolean equals(String o){
		//Variavel v = (Variavel) o;
		return (o.equals(this.nomeVariavel));
	}


}
