package GerenciaDeDados;


/** 
 *  Criada com o intuito de termos a estrutura Operacoes, para facilitar o uso da mesma no código e o mesmo ficar modularizado.
 *  Contém o nome da operacao a ser realizada ( read, write.... ), valor antigo e valor novo caso seja necessário e variável a ser
 *  lida, modificada, etc....
 */

/**
 * @author ERB & FAD
 *
 */
public class Operacao {
	
	String nomeOperacao;
	int valorAntigo;
	int valorNovo;
	//Transacao transacao;
	Variavel variavel;
	
	public Operacao(String nomeOperacao,int valorAntigo, int valorNovo, Transacao transacao, Variavel variavel){
		
		this.nomeOperacao = nomeOperacao;
		this.valorAntigo = valorAntigo;
		this.valorNovo = valorNovo;
		//this.transacao = transacao;
		this.variavel = variavel;
		
	}

	public String getNomeOperacao() {
		return nomeOperacao;
	}

	public int getValorAntigo() {
		return valorAntigo;
	}

	public int getValorNovo() {
		return valorNovo;
	}

/**	public Transacao getTransacao() {
		return transacao;
	}**/

	public Variavel getVariavel() {
		return variavel;
	}

	public void setNomeOperacao(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}

	public void setValorAntigo(int valorAntigo) {
		this.valorAntigo = valorAntigo;
	}

	public void setValorNovo(int valorNovo) {
		this.valorNovo = valorNovo;
	}

	/**public void setTransacao(Transacao transacao) {
		this.transacao = transacao;
	}**/

	public void setVariavel(Variavel variavel) {
		this.variavel = variavel;
	}

}
