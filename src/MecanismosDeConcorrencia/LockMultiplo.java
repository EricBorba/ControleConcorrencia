package MecanismosDeConcorrencia;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;

public class LockMultiplo {
	String transacao;
	Operacao operacao;
	Repositorio repositorio;


	public LockMultiplo(String transacaonova,Operacao operacaoNova,Repositorio repositorioNovo){
		this.operacao = operacaoNova;
		this.repositorio = repositorioNovo;
		this.transacao = transacaonova;

	}

	/**construindo o Lock, se conseguir bloquear retorna 1, se não conseguir bloquear porque a
	 * variavel estava sendo bloqueada por outra transacao retorna 2, se não bloqueou pq estava sendo bloqueada
	 * pela mesma transacao retorna 3*/

	public int construindoLockMultiplo(){

		int retorno = 0;
		int posicaoCrescerBloqueio = 0;
		int existeOutroBloqueio = 0;//verificar se existe outro read_lock com a mesma variavel
		
		//REVER TUDO
		if(operacao.getNomeOperacao() == "Read"){
			for(int i = 0; i < this.repositorio.getListaDeBloqueioMultiplo().size(); i++){
				if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(this.operacao.getVariavel())){
					if(!this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(transacao)){
						if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio() == "Read_lock"){

							retorno = 1;

						}else if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio() == "Write_lock"){
							
							/////bloqueada por outra com write_lock
							retorno = 2;
							i = this.repositorio.getListaDeBloqueioMultiplo().size();

						}else{

							System.out.println("ERRO,NOME DE BLOQUEIO ERRADO");
						}
					}else{
						///mesma transação
						retorno = 3;

					}
				}

			}

			if(retorno == 1 || retorno == 0){
				this.repositorio.adicionarBloqueioLista(transacao,operacao,1);
			}


		}else if(operacao.getNomeOperacao() == "Write"){

			for(int i = 0; i < this.repositorio.getListaDeBloqueioMultiplo().size(); i++){

				if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeVariavel().equals(this.operacao.getVariavel())){
					existeOutroBloqueio =  existeOutroBloqueio + 1;
					if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getNomeTransacao().equals(transacao)){
						if(this.repositorio.getListaDeBloqueioMultiplo().get(i).getModoBloqueio().equals("Read_lock")&& existeOutroBloqueio < 2){
							posicaoCrescerBloqueio = i;
							retorno = 1;
						}else if(existeOutroBloqueio > 2){

							retorno = 2;
						}else{
							retorno = 3;
						}
					}

				}

			}
			
			if(existeOutroBloqueio >= 2){
				retorno = 2;
			}else if(retorno == 0 && existeOutroBloqueio == 1){
				retorno = 2;
			}else if (retorno == 1 && existeOutroBloqueio < 2){
				this.repositorio.getListaDeBloqueioMultiplo().get(posicaoCrescerBloqueio).setModoBloqueio("Write_lock");
			}else if(retorno == 0 && existeOutroBloqueio == 0){
				
				this.repositorio.adicionarBloqueioLista(transacao,operacao,1);
			}

		}else{

			System.out.println("ERRO,NOME DE OPERAÇÃO ERRADA");

		}
		return retorno;
	}



/// fiz o acrescimo qndo se pede um write e tem um read da transação, falta verificar os outros casos 
	////acrescimo e decrescimo


}
