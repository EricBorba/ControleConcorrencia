package GerenciaDeDados;
/** 
 * Estrutura primitiva para ser possível guardar valores que serão mais tarde lidos, alterados ou removidos.
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
	
	/**Método criado para auxiliar na busca por uma variavel quando ela estiver dentro
	 * de uma lista, e consequentemente na remoção da mesma dessa lista**/
	public boolean equals(Object o){
		Variavel v = (Variavel) o;
		return (v.nomeVariavel.equals(this.nomeVariavel));
	}


}
