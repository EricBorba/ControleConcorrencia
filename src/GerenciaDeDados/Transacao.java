package GerenciaDeDados;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.crypto.Data;

/** 
 * Classe transacoes composta pelo nome que identifica a mesma e um ArrayList o qual contem as operacoes a serem realizadas pela transacoes.
 */

/**
 * @author ERB & FAD
 *
 */
public class Transacao {
	
	String nomeTransacao;
	ArrayList<Operacao> listaOperacoes;
	long tempoDeCriacao;
	GregorianCalendar calendar;
	Date data;
	
	public Transacao (String nomeTransacao){
		this.listaOperacoes = new ArrayList<Operacao>();
		this.nomeTransacao = nomeTransacao;
		calendar = new GregorianCalendar();  
		data = new Date();
		calendar.setTime(data);
		tempoDeCriacao = calendar.getTimeInMillis();
		}
		
	public ArrayList<Operacao> getListaOperacoes() {
		return listaOperacoes;
	}

	public void setListaOperacoes(ArrayList<Operacao> listaOperacoes) {
		this.listaOperacoes = listaOperacoes;
	}
	
	/**Metodo criado para auxiliar na busca por uma transacao quando ela estiver dentro
	 * de uma lista, e consequentemente na remocao da mesma dessa lista**/
	public boolean equals(String o){
		//Transacao t = (Transacao) o;
		return (o.equals(this.nomeTransacao));
	}
	
	/**Insere uma operacao dentro da lista de operacoes contida na transacao**/
	public void inserirOperacaoNaTransacao(Operacao operacao){
		this.listaOperacoes.add(operacao);
	}
	
	
	/**OS M�TODOS A SEGUIR PROVAVELMENTE NAO SERAO NECESSARIOS, DEVIDO A ISSO ESTAO COMENTADOS**/
	
    /** Para adicionar esse meodo e necessario a adicao de uma ID na estrutura Operacao; o mesmo seria capturado atraves de uma selecao na GUI	 * */
	
	/**public Operacao procurarOperacaoNaTransacao(){
				
	}
	
	public void removerOperacaoDaTransacao(Transacao transacao, Operacao operacao){
		
	}**/

	/**retorna o nome da transacao*/
	public String getnomeTransacao() {
		return this.nomeTransacao;
	}

	public long getTempoDeCriacao() {
		return tempoDeCriacao;
	}

	public long setTempoDeCriacao(long tempoDeCriacao) {
		return this.tempoDeCriacao = tempoDeCriacao;
	}
}
