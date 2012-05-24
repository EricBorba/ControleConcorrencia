package GerenciaDeDados;
import java.util.ArrayList;

/** 
 * Classe transações composta pelo nome que identifica a mesma e um ArrayList o qual contém as operaçoes a serem realizadas pela transação.
 */

/**
 * @author ERB & FAD
 *
 */
public class Transacao {
	
	String nomeTransacao;
	ArrayList<Operacao> listaOperacoes;
	
	public Transacao (String nomeTransacao){
		listaOperacoes = null;
		this.nomeTransacao = nomeTransacao;
	}

	public ArrayList<Operacao> getListaOperacoes() {
		return listaOperacoes;
	}

	public void setListaOperacoes(ArrayList<Operacao> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}

}
