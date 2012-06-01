package GUI;

import java.util.ArrayList;
import java.util.Vector;

import GerenciaDeDados.Transacao;
import GerenciaDeDados.Variavel;

public class LeitorDeListas {

	public void LeitorDeArquivos(){

	}
	/*retorna um vetor de variaveis*/
	public Vector RetornarVariaveis(ArrayList<Variavel> var){
		Vector<String> variaveis = new Vector<String>();

		int i =0;
		
		while(i < var.size()){
			variaveis.add(var.get(i).getNomeVariavel());
			i++;
		}
		return variaveis;
	}

	/*retorna um vetor de transações*/
	public Vector RetornarTrasacoes(ArrayList<Transacao> tran){
		Vector<String> transacoes = new Vector<String>();
		
		int i = 0;
		while(i < tran.size()){
  
			transacoes.add(tran.get(i).getnomeTransacao());
			i++;
			
		}

		return transacoes;
	}


}
