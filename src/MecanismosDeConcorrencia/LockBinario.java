package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;

public class LockBinario {
	Operacao operacao;
	Repositorio repositorio;
	String transacao;


	public LockBinario(String transacaoNova,Operacao operacaoNova, Repositorio repositorioNovo){

		this.operacao = operacaoNova;
		this.repositorio = repositorioNovo;
		this.transacao = transacaoNova;

	}	

	/**verifica a presença de uma variável na tabela de bloqueio, se ela 
	 * esta presente retorno false pois o bloqueio não pode ser feito pq ela já está bloqueada*/
	public boolean podeBloquear(){

		boolean bloqueado = false;
		for(int i = 0; i < this.repositorio.getListaDeBloqueiosBinarios().size(); i++){
			if(this.repositorio.getListaDeBloqueiosBinarios().get(i).getIdvariavel().equals(this.operacao.getVariavel()) == true){
				bloqueado = true;
			}

		}	
		return bloqueado;
	}

	/**construindo o Lock, se conseguir bloquear retorna 1, se não conseguir bloquear porque a
	 * variavel estava sendo bloqueada por outra transacao retorna 2, se não bloqueou pq estava sendo bloqueada
	 * pela mesma transacao retorna 3*/
	public int efetuarLockBinario(boolean bloqueio){
		int retorno = 0;
		String transacaoNaLista = "";

		if(bloqueio == true){
			this.repositorio.adicionarBloqueioLista(transacao,operacao,0);
			retorno = 1;
		}else{
			//verifica transacao presente na lista bloqueando a variavel procurada
			for(int i = 0; i < this.repositorio.getListaDeBloqueiosBinarios().size(); i++){
				if(this.repositorio.getListaDeBloqueiosBinarios().get(i).getIdvariavel().equals(this.operacao.getVariavel()) == true){

					transacaoNaLista = this.repositorio.getListaDeBloqueiosBinarios().get(i).getIdTransacao();
				}

			}

			if(transacaoNaLista.equals(transacao)){
				retorno = 3;

			}else if(!transacaoNaLista.equals(transacao)){
				retorno = 2;
				
			}

		}

		return retorno;
	}


}
