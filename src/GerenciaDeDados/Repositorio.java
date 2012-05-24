package GerenciaDeDados;
import java.util.ArrayList;



/**
	Classe que gerencia a camada de dados abaixo
 * 
 */

/**
 * @author ERB & FAD
 *
 */
public class Repositorio {

	ArrayList<Transacao> listaTransacoes;
	ArrayList<Variavel> listaVariaveis;
	
	/**
	 * 
	 */
	public Repositorio() {
		
		listaTransacoes = null;
		listaVariaveis = null;
				
	}
	
	/**Setor onde se cria, edita e remove os atributos globais da classe repositorio**/

	public ArrayList<Variavel> getListaVariaveis() {
		return listaVariaveis;
	}

	public void setListaVariaveis(ArrayList<Variavel> listaVariaveis) {
		this.listaVariaveis = listaVariaveis;
	}

	public ArrayList<Transacao> getTransacoes() {
		return listaTransacoes;
	}

	public void setTransacoes(ArrayList<Transacao> transacoes) {
		this.listaTransacoes = transacoes;
	}
	
	public int procurarTransacaoLista(String nomeTransacao){
		
		this.listaTransacoes.
	}
	
	public void adicionarTransacaoLista(Transacao transacao){
		
		this.listaTransacoes.add(transacao);		
	}
	
	public void removerTransacaoLista(String nomeTransacao){
		
		int posicaoTransacao = procurarTransacaoLista(nomeTransacao);
		this.listaTransacoes.remove(posicaoTransacao);	
	}
	
	public int procurarVariavelLista(String nomevariavel){
		
		this.listaTransacoes.
	}
	
	public void adicionarVariavelLista(Variavel variavel){
		
		this.listaVariaveis.add(variavel);		
	}
	
	public void removerVariavelLista(String nomeVariavel){
		
		int posicaoVariavel = procurarVariavelLista(nomeVariavel);
		this.listaVariaveis.remove(posicaoVariavel);	
	}
	
	/**Setor de criação das estruturas mais primitivas**/
	
	public Operacao criarOperacao(String nomeOperacao, int valorAntigo, int valorNovo, Transacao transacao, Variavel variavel){
		
		Operacao operacao;
		operacao = new Operacao(nomeOperacao, valorAntigo, valorNovo, transacao, variavel);  
		return operacao;
		
	}
	
	public Transacao criarTransacao(String nomeTransacao){
		
		Transacao transacao;
		transacao = new Transacao(nomeTransacao);
		return transacao;
		
	}
	
	public Variavel criarVariavel(String nomeVariavel, String valorVariavel){
		
		Variavel variavel;
		variavel = new Variavel(nomeVariavel, valorVariavel);
		return variavel;
		
	}	
	
	/**Métodos que envolvem a classe Transacao e operação.... já que uma transação tem uma lista de operações**/
	public void inserirOperacaoNaTransacao(Transacao transacao, Operacao operacao){
		transacao.listaOperacoes.add(operacao);
	}
	
    /** Para adicionar esse método é necessário a adição de uma ID na estrutura Operação; o mesmo seria capturado através de uma seleção na GUI	 * */
	
	public Operacao procurarOperacaoNaTransacao(){
		
	}
	
	public void removerOperacaoDaTransacao(Transacao transacao, Operacao operacao){
		
	}

}
