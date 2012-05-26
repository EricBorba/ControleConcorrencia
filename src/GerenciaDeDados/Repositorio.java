package GerenciaDeDados;
import java.util.ArrayList;



/**
	Classe que armazena a camada de dados. Possui uma lista de todas as transacoes e variáveis criadas.
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
	
	/**
	 * SETOR PARA CAPTURAR OU SETAR AS LISTAS DA CLASSE REPOSITORIO
	 * 
	 ***/

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
	
	/**
	 * SETOR PARA PROCURAR, ADICIONAR OU REMOVER TRANSACAOES E VARIAVEIS DENTRO DAS LISTAS DA CLASSE REPOSITORIO.
	 * **/
	
	/**Retorna a posicao da transacao dentro da lista de transações**/
	public Transacao procurarTransacaoLista(String nomeTransacao){
		
		return this.listaTransacoes.get(this.listaTransacoes.indexOf(nomeTransacao));
	}
	
	/**Adiciona uma transação dentro do arraylist de transacoes**/
	public void adicionarTransacaoLista(Transacao transacao){
		
		this.listaTransacoes.add(transacao);		
	}
	
	/**Remove uma transacao do arraylist de transacoes fazendo uso do "equals" criado na classe Transacao**/	
	public void removerTransacaoLista(String nomeTransacao){
		
		this.listaTransacoes.remove(this.listaTransacoes.indexOf(nomeTransacao));	
	}
	
	/**Procura uma variavel dentro do arraylist de variaveis**/
	public Variavel procurarVariavelLista(String nomeVariavel){
				
		return this.listaVariaveis.get(this.listaVariaveis.indexOf(nomeVariavel));
	}
	
	/**Adiciona uma variavel dentro do arraylist de variaveis**/
	public void adicionarVariavelLista(Variavel variavel){
		
		this.listaVariaveis.add(variavel);		
	}
	
	/**Remove uma variavel dentro do arraylist de variaveis fazendo uso do "equals" criado na classe Variavel**/
	public void removerVariavelLista(String nomeVariavel){
		
		this.listaVariaveis.remove(this.listaVariaveis.indexOf(nomeVariavel));
	}
	
	/**
	 * SETOR DE CRIAÇÃO DAS ESTRUTURAS MAIS PRIMITIVAS ---- TALVEZ NÃO TENHA NECESSIDADE
	 * **/
	
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
