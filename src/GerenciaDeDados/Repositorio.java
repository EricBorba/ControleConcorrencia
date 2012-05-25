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
		
		this.listaTransacoes = new ArrayList<Transacao>();
		this.listaVariaveis = new ArrayList<Variavel>();
				
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
	
	/**Retorna a posicao da transacao dentro da lista de transações**/
	public int procurarTransacaoLista(String nomeTransacao){
		
		int posicao = 0;
		
		for(Transacao transacao: this.listaTransacoes){
			if(nomeTransacao.equals(transacao.nomeTransacao)){
				posicao = this.listaTransacoes.indexOf(transacao);
			}
		}
		
		return posicao;
	}
	
	/**Adiciona uma transação dentro do arraylist de transacoes**/
	public void adicionarTransacaoLista(Transacao transacao){
		
		this.listaTransacoes.add(transacao);		
	}
	
	/**Remove uma transacao do arraylist de transacoes**/	
	public void removerTransacaoLista(String nomeTransacao){
		
		this.listaTransacoes.remove(this.listaTransacoes.indexOf(nomeTransacao));	
	}
	
	/**Procura uma variavel dentro do arraylist de variaveis**/
	public int procurarVariavelLista(String nomeVariavel){
		
		int posicao = 0;
		
		for(Variavel variavel: this.listaVariaveis){
			if(nomeVariavel.equals(variavel.nomeVariavel)){
				posicao = this.listaVariaveis.indexOf(variavel);
			}
		}
		
		return posicao;
	}
	
	/**Adiciona uma variavel dentro do arraylist de variaveis**/
	public void adicionarVariavelLista(Variavel variavel){
		
		this.listaVariaveis.add(variavel);		
	}
	
	/**Remove uma variavel dentro do arraylist de variaveis**/
	public void removerVariavelLista(String nomeVariavel){
		
		this.listaVariaveis.remove(this.listaVariaveis.indexOf(nomeVariavel));
	}
	
	/**Setor de criação das estruturas mais primitivas---- talvez n tenha necessidade**/
	
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
	
	

}
