import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ERB & FAD
 *
 */
public class Transacao {
	
	ArrayList<String> listaOperacoes;
	
	public Transacao (){
		listaOperacoes = null;
	}

	public ArrayList<String> getListaOperacoes() {
		return listaOperacoes;
	}

	public void setListaOperacoes(ArrayList<String> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}

}
