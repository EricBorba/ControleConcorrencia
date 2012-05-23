import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ERB & FAD
 *
 */
public class Transacao {
	
	ArrayList<Operacao> listaOperacoes;
	
	public Transacao (){
		listaOperacoes = null;
	}

	public ArrayList<Operacao> getListaOperacoes() {
		return listaOperacoes;
	}

	public void setListaOperacoes(ArrayList<Operacao> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}

}
