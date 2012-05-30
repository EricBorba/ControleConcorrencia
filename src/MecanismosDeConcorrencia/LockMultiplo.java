package MecanismosDeConcorrencia;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;

public class LockMultiplo {
	Transacao transacao;
	Operacao operacao;
	Repositorio repositorio;
	int numeroDeLeituras;


	public LockMultiplo(Transacao transacaonova,Operacao operacaoNova,Repositorio repositorioNovo,int numeroDeLeiturasNovo){
		this.operacao = operacaoNova;
		this.repositorio = repositorioNovo;
		this.transacao = transacaonova;
		this.numeroDeLeituras = numeroDeLeiturasNovo;

	}
	

	/**construindo o Lock, se conseguir bloquear retorna 1, se não conseguir bloquear porque a
	 * variavel estava sendo bloqueada por outra transacao retorna 2, se não bloqueou pq estava sendo bloqueada
	 * pela mesma transacao retorna 3*/

	public boolean construindoLockMultiplo(){

		boolean retorno = true;
		int caso = 0;
		int posicaoCrescerBloqueio = 0;
		int existeOutroBloqueio = 0;//verificar se existe outro read_lock com a mesma variavel

		if(operacao.getNomeOperacao() == "Read"){
			for(int i = 0; i < this.repositorio.getListaDeBloqueioMultiplo().size(); i++){
				if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(this.operacao.getVariavel())){
					if(!this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(transacao.getnomeTransacao())){
						if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio() == "Read_lock"){
							retorno = true;
							caso = 1;

						}else if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio() == "Write_lock"){
							retorno = false;
							/////bloqueada por outra com write_lock
							caso = 2;
							i = this.repositorio.getListaDeBloqueioMultiplo().size();

						}else{

							System.out.println("ERRO,NOME DE BLOQUEIO ERRADO");
						}
					}else{
						///mesma transação ou com read ou com write
						caso = 3;
						retorno = true;

					}
				}

			}

			if(caso == 1 || caso == 0){

				//modificarNumerodeReadsNaTabeladeBloqueio();				
				this.repositorio.adicionarBloqueioListaLockMultiplo(transacao.getnomeTransacao(),operacao,(numeroDeLeituras - 1));

			}


		}else if(operacao.getNomeOperacao() == "Write"){

			for(int i = 0; i < this.repositorio.getListaDeBloqueioMultiplo().size(); i++){

				if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(this.operacao.getVariavel())){
					existeOutroBloqueio =  existeOutroBloqueio + 1;
					if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(transacao.getnomeTransacao())){
						if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio().equals("Read_lock")&& existeOutroBloqueio < 2){
							retorno = true;
							posicaoCrescerBloqueio = i;
							caso = 1;
						}else if(existeOutroBloqueio > 2){
							retorno = false;
							caso = 2;
						}else{
							retorno = true;
							caso = 3;
						}
					}

				}

			}

			if(existeOutroBloqueio >= 2){
				retorno = false;
				caso = 2;
			}else if(caso == 0 && existeOutroBloqueio == 1){
				retorno = false;
				caso = 2;
			}else if (caso == 1 && existeOutroBloqueio < 2){
				retorno = true;
				this.repositorio.getListaDeBloqueioMultiplo().get(posicaoCrescerBloqueio).setModoBloqueio("Write_lock");
			}else if(caso == 0 && existeOutroBloqueio == 0){
				retorno = true;				
				this.repositorio.adicionarBloqueioListaLockMultiplo(transacao.getnomeTransacao(),operacao,numeroDeLeituras);
			}

		}else{

			System.out.println("ERRO,NOME DE OPERAÇÃO ERRADA");

		}
		return retorno;
	}



	/// fazer um metodo para retornar o numero de reads de uma determinada transição
	///fazer um método para decrescimento

//
//	/**após fazer um read decrementar em todas as operações da transacao utilizada*/
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
	public int retornarNumerodeReads(Transacao t, Operacao o){
		int numeroRetorno = 0;
		for(int i = 0; i < this.repositorio.getListaDeBloqueioMultiplo().size();i++){
			if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(t.getnomeTransacao()) && this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(o.getVariavel())){

				numeroRetorno = this.repositorio.getListaDeBloqueioMultiplo().get(i).getNumeroDeLeituras();
				i = this.repositorio.getListaDeBloqueioMultiplo().size();
			}

		}

		return numeroRetorno;

	}

	/**fazer decrescimo*/

	public void realizarRelaxamento(Transacao t, Operacao o){
		
		for(int i = 0; i < this.repositorio.getListaDeBloqueioMultiplo().size();i++){
			
			if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(t.getnomeTransacao()) && this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(o.getVariavel())){
				
				this.repositorio.getListaDeBloqueioMultiplo().get(i).setModoBloqueio("Read_lock");
				i = this.repositorio.getListaDeBloqueioMultiplo().size();
			}
			
		}

	}
	
	/**remover transação da lista de bloqueios multiplo*/
	public void unlockMultiplo(Transacao t, Operacao o){
		
		this.repositorio.removerBloqueioListaMultipla(t, o);
		
	}

}
