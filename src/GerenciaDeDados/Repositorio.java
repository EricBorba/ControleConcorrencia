package GerenciaDeDados;
import java.util.ArrayList;

import MecanismosDeConcorrencia.Bloqueio;



/**
	Classe que armazena a camada de dados. Possui uma lista de todas as transacoes e vari‡veis criadas.
 * 
 */

/**
 * @author ERB & FAD
 *
 */
public class Repositorio {

	ArrayList<Transacao> listaTransacoes;
	ArrayList<Variavel> listaVariaveis;
	ArrayList<Bloqueio> listaDeBloqueios;

	/**
	 * 
	 */
	public Repositorio() {

		this.listaTransacoes = new ArrayList<Transacao>();
		this.listaVariaveis = new ArrayList<Variavel>();
		this.listaDeBloqueios = new ArrayList<Bloqueio>();		
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

	/**Retorna a posicao da transacao dentro da lista de transacoes**/
	public Transacao procurarTransacaoLista(String nomeTransacao){

		return this.listaTransacoes.get(this.listaTransacoes.indexOf(nomeTransacao));
	}

	/**Adiciona uma transacao dentro do arraylist de transacoes**/
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
	 * SETOR DE CRIA‚ÌO DAS ESTRUTURAS MAIS PRIMITIVAS ---- TALVEZ NÌO TENHA NECESSIDADE
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


	/**tabela de  bloqueio*/


	public ArrayList<Bloqueio> getListaDeBloqueios() {
		return listaDeBloqueios;
	}

	public void setListaDeBloqueios(ArrayList<Bloqueio> listaDeBloqueios) {
		this.listaDeBloqueios = listaDeBloqueios;
	}


	/**Remove um bloqueio dentro do arraylist de bloqueios **/
	public void removerBloqueioLista(String nomeTransacao,String nomeVariavel){
		int posicao = 0;
		for(int i = 0; i < this.listaDeBloqueios.size(); i++){
			if(this.listaDeBloqueios.get(i).getIdTransacao().equals(nomeTransacao) == true && this.listaDeBloqueios.get(i).equals(nomeVariavel)== true){
				posicao = i;
			}

		}

		this.listaDeBloqueios.remove(posicao);
	}

	/**adiciona um bloqueio dentro do arraylist de bloqueios **/
	public void adicionarBloqueioLista(String nomeTransacao,String nomeVariavel, String tipoBloqueio){
		Bloqueio novoBloqueio = new Bloqueio(nomeTransacao, tipoBloqueio, nomeVariavel);

		this.listaDeBloqueios.add(novoBloqueio);
	}
	
	


}
