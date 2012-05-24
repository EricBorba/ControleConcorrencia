package GUI;

import java.util.Vector;

public class LeitorDeArquivos {

	public void LeitorDeArquivos(){

	}

	public Vector RetornarVariaveis(){
		Vector<String> variaveis = new Vector<String>();

		Arquivo arq = new Arquivo("variaveis.txt", "lixo.txt");

		while(!arq.isEndOfFile()){
			variaveis.add(arq.acharQuebra(arq));
		}
		return variaveis;
	}

	public Vector RetornarTrasacoes(){
		Vector<String> transacoes = new Vector<String>();
		Arquivo arq = new Arquivo("transacoes.txt", "lixo.txt");

		while(!arq.isEndOfFile()){

			transacoes.add(arq.acharQuebra(arq));
			
		}

		return transacoes;
	}


}
