package GerenciaDeDados;
import java.util.ArrayList;




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
	ArrayList<Variavel> listaVariaveisAntigas;
	ArrayList<BloqueioLockBinario> listaDeBloqueiosBinario;
	ArrayList<BloqueioLockMultiplo> listaDeBloqueioMultiplo;

	/**
	 * 
	 */
	public Repositorio() {

		this.listaTransacoes = new ArrayList<Transacao>();
		this.listaVariaveis = new ArrayList<Variavel>();
		this.listaDeBloqueiosBinario = new ArrayList<BloqueioLockBinario>();		
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
		operacao = new Operacao(nomeOperacao, valorAntigo, valorNovo, variavel);  
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


	public ArrayList<BloqueioLockBinario> getListaDeBloqueiosBinarios() {
		return listaDeBloqueiosBinario;
	}

	public void setListaDeBloqueiosBinarios(ArrayList<BloqueioLockBinario> listaDeBloqueioBinarios) {
		this.listaDeBloqueiosBinario = listaDeBloqueioBinarios;
	}


	public ArrayList<BloqueioLockMultiplo> getListaDeBloqueioMultiplo() {
		return listaDeBloqueioMultiplo;
	}

	public void setListaDeBloqueioMultiplo(ArrayList<BloqueioLockMultiplo> listaDeBloqueioMultiplo) {
		this.listaDeBloqueioMultiplo = listaDeBloqueioMultiplo;
	}

	/**Remove um bloqueio dentro do arraylist de bloqueios binarios**/
	public void removerBloqueioListaBinaria(Transacao t,Operacao operacao){

				int posicao = 0;
			for(int i = 0; i < this.listaDeBloqueiosBinario.size(); i++){
				if(this.listaDeBloqueiosBinario.get(i).getIdTransacao().equals(t.getnomeTransacao()) == true && this.listaDeBloqueiosBinario.get(i).equals(operacao.getVariavel())== true){
					posicao = i;
				}

			}
			this.listaDeBloqueiosBinario.remove(posicao);

		}
	/**Remove um bloqueio dentro do arraylist de bloqueios multiplo**/
	public void removerBloqueioListaMultipla(Transacao t,Operacao operacao){

			int posicao = 0;
			for(int i = 0; i < this.listaDeBloqueioMultiplo.size(); i++){
				if(this.listaDeBloqueioMultiplo.get(i).getNomeTransacao().equals(t.getnomeTransacao()) && this.listaDeBloqueioMultiplo.get(i).equals(operacao.getVariavel())){
					posicao = i;
				}

			}
			this.listaDeBloqueioMultiplo.remove(posicao);
		
	}

	/**adiciona um bloqueio dentro do arraylist de bloqueios lockmultiplo*/
	public void adicionarBloqueioListaLockMultiplo(String nomeTransacao, Operacao operacao, int numeroDeleituras){

			
			BloqueioLockMultiplo novoBloqueio = new BloqueioLockMultiplo(nomeTransacao, operacao.getVariavel().getNomeVariavel(), operacao.getNomeOperacao()+"_lock",numeroDeleituras);
			this.listaDeBloqueioMultiplo.add(novoBloqueio);
		


	}

	/**adiciona um bloqueio dentro do arraylist de bloqueios lockbinario*/
	public void adicionarBloqueioListaLockBinario(String nomeTransacao, Operacao operacao){

			BloqueioLockBinario novoBloqueio = new BloqueioLockBinario(nomeTransacao, operacao.getVariavel().getNomeVariavel());
			this.listaDeBloqueiosBinario.add(novoBloqueio);

	}
	
	/**guarda em um arraylist as a variavel imediatamente antes dela ser modificada*/
	public void ValoresAntigosVariaveis(Variavel variavel){
		
		this.listaVariaveisAntigas.add(variavel);
	}

	public ArrayList<Variavel> getListaVariaveisAntigas() {
		return listaVariaveisAntigas;
	}

	public void setListaVariaveisAntigas(ArrayList<Variavel> listaVariaveisAntigas) {
		this.listaVariaveisAntigas = listaVariaveisAntigas;
	}
	
	
}
