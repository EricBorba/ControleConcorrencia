package MecanismosDeConcorrencia;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;

public class LockMultiplo {

	long tempoDaTransacaoBloqueada;

	public LockMultiplo(){
		

	}


	/**construindo o Lock, se conseguir bloquear retorna 1, se n�o conseguir bloquear porque a
	 * variavel estava sendo bloqueada por outra transacao retorna 2, se n�o bloqueou pq estava sendo bloqueada
	 * pela mesma transacao retorna 3*/

	public int construindoLockMultiplo(Repositorio repositorio,Transacao transacao,Operacao operacao){

		//boolean retorno = true;
		int caso = 0;
		int posicaoCrescerBloqueio = 0;
		int existeOutroBloqueio = 0;//verificar se existe outro read_lock com a mesma variavel

		if(operacao.getNomeOperacao() == "Read"){
			for(int i = 0; i < repositorio.getListaDeBloqueioMultiplo().size(); i++){
				if(repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(operacao.getVariavel())){
					if(!repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(transacao.getnomeTransacao())){
						if(repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio() == "Read_lock"){
							caso = 1;

						}else if(repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio() == "Write_lock"){
							
							/////bloqueada por outra com write_lock
							caso = 2;
								
							/// localizando transacao que bloqueiou na lista de transacoes do repositorio
							///para retornar seu timestamping
							int posBloqueio = 0;
							for(int h = 0;h < repositorio.getTransacoes().size();h++){

								if(repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(repositorio.getTransacoes().get(h).getnomeTransacao())){
									posBloqueio = h;
									h = repositorio.getTransacoes().size();
								}

							}
							
							tempoDaTransacaoBloqueada = repositorio.getTransacoes().get(posBloqueio).getTempoDeCriacao();
							i = repositorio.getListaDeBloqueioMultiplo().size();
						}else{

							System.out.println("ERRO,NOME DE BLOQUEIO ERRADO");
						}
					}else{
						///mesma transa��o ou com read ou com write
						caso = 3;
						
					}
				}

			}

			if(caso == 1 || caso == 0){

				//modificarNumerodeReadsNaTabeladeBloqueio();				
				repositorio.adicionarBloqueioListaLockMultiplo(transacao.getnomeTransacao(),operacao);

			}


		}else if(operacao.getNomeOperacao() == "Write"){

			for(int i = 0; i < repositorio.getListaDeBloqueioMultiplo().size(); i++){

				if(repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(operacao.getVariavel())){
					existeOutroBloqueio =  existeOutroBloqueio + 1;
					if(repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(transacao.getnomeTransacao())){
						if(repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio().equals("Read_lock")&& existeOutroBloqueio < 2){
							posicaoCrescerBloqueio = i;
							caso = 1;
						}else if(existeOutroBloqueio > 2){
							caso = 2;
						}else{
							
							caso = 3;
						}
					}

				}

			}

			if(existeOutroBloqueio >= 2){
				
				caso = 2;
			}else if(caso == 0 && existeOutroBloqueio == 1){
				
				caso = 2;
			}else if (caso == 1 && existeOutroBloqueio < 2){
				
				repositorio.getListaDeBloqueioMultiplo().get(posicaoCrescerBloqueio).setModoBloqueio("Write_lock");
			}else if(caso == 0 && existeOutroBloqueio == 0){
							
				repositorio.adicionarBloqueioListaLockMultiplo(transacao.getnomeTransacao(),operacao);
			}

		}else{

			System.out.println("ERRO,NOME DE OPERA��O ERRADA");

		}
		return caso;
	}



	/// fazer um metodo para retornar o numero de reads de uma determinada transi��o
	///fazer um m�todo para decrescimento

	//
	//	/**ap�s fazer um read decrementar em todas as opera��es da transacao utilizada*/
	//	public void modificarNumerodeReadsNaTabeladeBloqueio (){
	//
	//		for(int i = 0; i < this.repositorio.getListaDeBloqueioMultiplo().size();i++){
	//
	//			if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(transacao.getnomeTransacao())){
	//				this.repositorio.getListaDeBloqueioMultiplo().get(i).setNumeroDeLeituras(numeroDeLeituras-1);
	//
	//			}
	//
	//		}
	//
	//	}

	/**retornar o numero de reads de uma dada transacao*/
	public int retornarNumerodeReads(Transacao t, Operacao o,Repositorio repositorio){
		int numeroRetorno = 0;
		for(int i = 0; i < repositorio.getListaDeBloqueioMultiplo().size();i++){
			if(repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(t.getnomeTransacao()) && repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(o.getVariavel())){

				numeroRetorno = repositorio.getListaDeBloqueioMultiplo().get(i).getNumeroDeLeituras();
				i = repositorio.getListaDeBloqueioMultiplo().size();
			}

		}

		return numeroRetorno;

	}

	/**fazer decrescimo*/

	public void realizarRelaxamento(Transacao t, Operacao o,Repositorio repositorio){

		for(int i = 0; i < repositorio.getListaDeBloqueioMultiplo().size();i++){

			if(repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(t.getnomeTransacao()) && repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(o.getVariavel())){

				repositorio.getListaDeBloqueioMultiplo().get(i).setModoBloqueio("Read_lock");
				i = repositorio.getListaDeBloqueioMultiplo().size();
			}

		}

	}

	/**remover transa��o da lista de bloqueios multiplo*/
	public void unlockMultiplo(Transacao t, Operacao o,Repositorio repositorio){

		repositorio.removerBloqueioListaMultipla(t, o);

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

	/**get e set do timestamping*/
	public long getTempoDaTransacaoBloqueada() {
		return tempoDaTransacaoBloqueada;
	}


	public void setTempoDaTransacaoBloqueada(int tempoDaTransacaoBloqueada) {
		this.tempoDaTransacaoBloqueada = tempoDaTransacaoBloqueada;
	}
	

	
	

}
