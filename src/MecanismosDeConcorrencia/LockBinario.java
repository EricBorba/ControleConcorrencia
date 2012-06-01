package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;

public class LockBinario {
	Operacao operacao;
	String transacao;


	public LockBinario(String transacaoNova,Operacao operacaoNova){

		this.operacao = operacaoNova;
		this.transacao = transacaoNova;

	}	

	/**verifica a presença de uma variável na tabela de bloqueio, se ela 
	 * esta presente retorno false pois o bloqueio não pode ser feito pq ela já está bloqueada*/
	public boolean podeBloquear(Repositorio repositorio){

		boolean bloqueado = false;
		for(int i = 0; i < repositorio.getListaDeBloqueiosBinarios().size(); i++){
			if(repositorio.getListaDeBloqueiosBinarios().get(i).getIdvariavel().equals(this.operacao.getVariavel())){
				bloqueado = true;
			}

		}	
		return bloqueado;
	}

	/**construindo o Lock, se conseguir bloquear retorna 1, se não conseguir bloquear porque a
	 * variavel estava sendo bloqueada por outra transacao retorna 2, se não bloqueou pq estava sendo bloqueada
	 * pela mesma transacao retorna 3*/
	public boolean efetuarLockBinario(boolean bloqueio,Repositorio repositorio){
		boolean retorno = true;
		String transacaoNaLista = "";

		if(bloqueio == true){
			repositorio.adicionarBloqueioListaLockBinario(transacao,operacao);
			retorno = true;
		}else{
			//verifica transacao presente na lista bloqueando a variavel procurada
//			for(int i = 0; i < this.repositorio.getListaDeBloqueiosBinarios().size(); i++){
//				if(this.repositorio.getListaDeBloqueiosBinarios().get(i).getIdvariavel().equals(this.operacao.getVariavel()) == true){
//
//					transacaoNaLista = this.repositorio.getListaDeBloqueiosBinarios().get(i).getIdTransacao();
//				}
//
//			}
//
//			if(transacaoNaLista.equals(transacao)){
//				retorno = 3;
//
//			}else if(!transacaoNaLista.equals(transacao)){
//				retorno = 2;
//				
//			}
			retorno = false;

		}

		return retorno;
	}

	/**remover transação da lista de bloqueios binario*/
	public void unlockBinario(Transacao t, Operacao o,Repositorio repositorio){
		
		repositorio.removerBloqueioListaBinaria(t, o);
		
	}
	
	/**remove todas as operacoes de uma dada transacao na lista de bloqueiosbinarios*/
	public void unlockTodasAsOperacoesdaTransacao(Transacao t,Repositorio repositorio){
				
		for(int i = 0; i < repositorio.getListaDeBloqueioMultiplo().size(); i++){
			if(repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(t.getnomeTransacao())){
				repositorio.getListaDeBloqueioMultiplo().remove(i);
				i = 0;
			}

		}
		
		
	}

}
