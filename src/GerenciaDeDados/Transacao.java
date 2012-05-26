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
		this.listaOperacoes = new ArrayList<Operacao>();
		this.nomeTransacao = nomeTransacao;
	}

	public ArrayList<Operacao> getListaOperacoes() {
		return listaOperacoes;
	}

	public void setListaOperacoes(ArrayList<Operacao> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}
	
	/**Método criado para auxiliar na busca por uma transação quando ela estiver dentro
	 * de uma lista, e consequentemente na remoção da mesma dessa lista**/
	public boolean equals(String o){
		//Transacao t = (Transacao) o;
		return (o.equals(this.nomeTransacao));
	}
	
	/**Insere uma operação dentro da lista de operações contida na transação**/
	public void inserirOperacaoNaTransacao(Operacao operacao){
		this.listaOperacoes.add(operacao);
	}
	
	
	/**OS MÉTODOS A SEGUIR PROVAVELMENTE NÃO SERÃO NECESSÁRIOS, DEVIDO A ISSO ESTÃO COMENTADOS**/
	
    /** Para adicionar esse método é necessário a adição de uma ID na estrutura Operação; o mesmo seria capturado através de uma seleção na GUI	 * */
	
	/**public Operacao procurarOperacaoNaTransacao(){
				
	}
	
	public void removerOperacaoDaTransacao(Transacao transacao, Operacao operacao){
		
	}**/

}
