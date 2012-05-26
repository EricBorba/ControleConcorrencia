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
		this.listaOperacoes = new ArrayList<Operacao>();
		this.nomeTransacao = nomeTransacao;
	}

	public ArrayList<Operacao> getListaOperacoes() {
		return listaOperacoes;
	}

	public void setListaOperacoes(ArrayList<Operacao> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}
	
	/**M�todo criado para auxiliar na busca por uma transa��o quando ela estiver dentro
	 * de uma lista, e consequentemente na remo��o da mesma dessa lista**/
	public boolean equals(String o){
		//Transacao t = (Transacao) o;
		return (o.equals(this.nomeTransacao));
	}
	
	/**Insere uma opera��o dentro da lista de opera��es contida na transa��o**/
	public void inserirOperacaoNaTransacao(Operacao operacao){
		this.listaOperacoes.add(operacao);
	}
	
	
	/**OS M�TODOS A SEGUIR PROVAVELMENTE N�O SER�O NECESS�RIOS, DEVIDO A ISSO EST�O COMENTADOS**/
	
    /** Para adicionar esse m�todo � necess�rio a adi��o de uma ID na estrutura Opera��o; o mesmo seria capturado atrav�s de uma sele��o na GUI	 * */
	
	/**public Operacao procurarOperacaoNaTransacao(){
				
	}
	
	public void removerOperacaoDaTransacao(Transacao transacao, Operacao operacao){
		
	}**/

}
