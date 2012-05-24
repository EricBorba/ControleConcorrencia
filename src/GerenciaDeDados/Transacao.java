package GerenciaDeDados;
import java.util.ArrayList;

/** 
 * Classe transa��es composta pelo nome que identifica a mesma e um ArrayList o qual cont�m as opera�oes a serem realizadas pela transa��o.
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
