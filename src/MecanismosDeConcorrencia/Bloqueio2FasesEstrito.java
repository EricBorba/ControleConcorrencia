package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Repositorio;
import GerenciaDeDados.Transacao;

public class Bloqueio2FasesEstrito {



	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<String> listaOperacoesFinal;
	ArrayList<String> listaOperacoesOficial;

	LockMultiplo lock;	

	public Bloqueio2FasesEstrito(ArrayList<Transacao> listaTransacoesRecebida){
		this.listaTransacoesRecebida = new ArrayList<Transacao>();
		this.listaOperacoesFinal = new ArrayList<String>();
		this.listaOperacoesOficial = new ArrayList<String>();
		this.lock = new LockMultiplo();

	}	

	/**
	 * Caracterizado por liberar os bloqueios "exclusivos" apenas apos um commit ou abort.
	 * **/
	public ArrayList<String> executar(Repositorio repositorio){



		// Fazendo um copia da lista de transacoes do repositorio para que o mesmo permaneca inalterado.
		for(int j=0; j < repositorio.getTransacoes().size(); j++){


			Transacao transacaoRecebidaTemp = new Transacao(repositorio.getTransacoes().get(j).getnomeTransacao());
			ArrayList<Operacao> operacaoRecebidaTemp = new ArrayList<Operacao>();

			for(int z=0; z < repositorio.getTransacoes().get(j).getListaOperacoes().size(); z++){

				operacaoRecebidaTemp.add(repositorio.getTransacoes().get(j).getListaOperacoes().get(z));

			}

			transacaoRecebidaTemp.setListaOperacoes(operacaoRecebidaTemp);
			this.listaTransacoesRecebida.add(transacaoRecebidaTemp);


		}

		int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		int posicaoTransacaoLista=0;   // Para saber qual transacao esta a ser executada no momento
		int conseguiuExecutar = 0;
		Operacao operacaoTemp;
		ArrayList<Transacao> listaTransacaoAuxiliar = new ArrayList<Transacao>(); // Para auxiliar na verificacao de bloqueios futuros


		for(int a=0; a < listaTransacoesRecebida.size(); a++){
			Transacao transacaoAuxiliar = new Transacao(listaTransacoesRecebida.get(a).getnomeTransacao());
			listaTransacaoAuxiliar.add(a, transacaoAuxiliar);

		}

		//Polling criado para ficar alternando entre as transacoes e realizando( se possivel ) uma operacao de cada transacao por vez.
		while(!this.listaTransacoesRecebida.isEmpty()){					

			lock.setTempoDaTransacaoBloqueada(0); //Para zerar e evitar problemas

			if(posicaoTransacaoLista < quantidadeTransacoes){

				Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que esta na vez.

				if(transacaoTemp.getListaOperacoes().size() != 0){ 

					operacaoTemp = transacaoTemp.getListaOperacoes().get(0);//Pega sempre a primeira posicao da lista de operacoes


					if(!operacaoTemp.getNomeOperacao().equals("Commit") && !operacaoTemp.getNomeOperacao().equals("Begin") && !operacaoTemp.getNomeOperacao().equals("End")){


						conseguiuExecutar = lock.construindoLockMultiplo(repositorio, transacaoTemp, operacaoTemp); // Tenta executar a operacao chamando o lockMultiplo.

						//Se foi possivel de executar
						if(conseguiuExecutar != 2){

							RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(), repositorio, conseguiuExecutar);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
							this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(0);//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
							listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().add(operacaoTemp); // usada para se fazer comparacoes, afinal ela vai conter as operacoes que ja foram executadas

							if(!operacaoTemp.getNomeOperacao().equals("Write")){

								//Se nao houver write ou read de alguma variavel que nao foi bloqueada ainda || se nao houver passagem de read para write de alguam variavel qualquer. || Se houver algum read dessa variavel em questao que esta dando read agora
								if(!existeBloqueiosFuturos(listaTransacaoAuxiliar, posicaoTransacaoLista, operacaoTemp)){

									lock.unlockMultiplo(transacaoTemp, operacaoTemp, repositorio);

								}


							}



							// Aqui entra o wait-die	
						}/**else{

							// Ou seja, a operacao da transacao nao foi executada e ela eh mais nova que a transacao que lhe bloqueou.
							if(transacaoTemp.getTempoDeCriacao() > lock.getTempoDaTransacaoBloqueada()){

								for(int i=0; i < repositorio.getTransacoes().size() ; i++){

									// a transacao em execucao por ser mais nova sera dado rollback ou seja, fazemos uso do repositorio como um backup pra que todas as suas operacoes voltem ao estado original. E o timestamp da mesma continua sendo o original.
									if(this.listaTransacoesRecebida.get(posicaoTransacaoLista).getnomeTransacao().equals(repositorio.getTransacoes().get(i).getnomeTransacao())){

										this.listaTransacoesRecebida.get(posicaoTransacaoLista).setListaOperacoes(repositorio.getTransacoes().get(i).getListaOperacoes());

									}

								}


								apagaTransacaoRollBackOficial(transacaoTemp.getnomeTransacao(), listaTransacaoAuxiliar, repositorio);


							}

						}**/


					}else if(operacaoTemp.getNomeOperacao().equals("Begin") || operacaoTemp.getNomeOperacao().equals("End")){
						RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(),repositorio, conseguiuExecutar); // Adiciona begin a lista final
						this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(0); // remove a operacao da lista de operacoes.


						// Ou seja, eh Commit
					}else{
						RetornoOperacaoString(operacaoTemp,transacaoTemp.getnomeTransacao(), repositorio, conseguiuExecutar); // Adiciona commit a lista final
						this.listaTransacoesRecebida.remove(posicaoTransacaoLista); // Nao precisa remover a operacao da lista de operacoes pois sera mandado remover a transacao toda.
						listaTransacaoAuxiliar.remove(posicaoTransacaoLista);
						lock.unlockTodasAsOperacoesdaTransacao(transacaoTemp, repositorio); // Metodo a ser criado no lock, desbloqueia todas as variaveis bloqueadas por determinada transacao
					}


					//Polling pra passar de uma transacao para outra
					if(operacaoTemp.getNomeOperacao().equals("Commit")){

						if(posicaoTransacaoLista == (listaTransacoesRecebida.size())){
							posicaoTransacaoLista = 0;
						}// Nao tem else pq a posicao tem que permanecer a mesma, afinal apos remover um objeto de uma lista, o seguinte ocupa seu lugar.

					}else{
						if(posicaoTransacaoLista == (listaTransacoesRecebida.size()-1)){
							posicaoTransacaoLista = 0; //Caso seja a ultima transacao, retorna para a primeira.
						}else{
							posicaoTransacaoLista++;
						}

					}	

				}else{
					posicaoTransacaoLista = 0 ;
				}

			}else{
				posicaoTransacaoLista = 0;
			}

			quantidadeTransacoes = this.listaTransacoesRecebida.size();

		}

		return this.listaOperacoesOficial;
	}


	/**Passa como parametro o nome da transacao e apaga todas as operacoes
	 * referentes a ela da lista principal
	 * @param listaTransacaoAuxiliar */
	private void apagaTransacaoRollBackOficial(String nomeTransacao, ArrayList<Transacao> listaTransacaoAuxiliar, Repositorio repositorio) {

		for(int i = 0; i < listaOperacoesOficial.size(); i++){

			String [] temp = listaOperacoesOficial.get(i).split(" ");
			if(temp[0].equals(nomeTransacao)){

				listaOperacoesOficial.remove(i);

			}
		}	

		for(int j = 0; j < listaTransacaoAuxiliar.size(); j++){


			if(listaTransacaoAuxiliar.get(j).getnomeTransacao().equals(nomeTransacao)){

				listaTransacaoAuxiliar.get(j).getListaOperacoes().clear();

			}
		}	

		for(int g =0 ; g < repositorio.getListaDeBloqueioMultiplo().size(); g++){

			if(repositorio.getListaDeBloqueioMultiplo().get(g).getNomeTransacao().equals(nomeTransacao)){

				repositorio.getListaDeBloqueioMultiplo().remove(g);
			}

		}

	}


	/**lembrar de acrescentar o numero de reads qndo acrecentar a variavel*/
	public void RetornoOperacaoString(Operacao o, String nomeTransacao, Repositorio rep,int motivoDaEscrita) {
		String retorno= "";
		int j = 0;		
		int posicaoVariavel = 0;

		for(j = 0 ; j < rep.getListaVariaveis().size();j++){
			if(rep.getListaVariaveis().get(j).getNomeVariavel().equals(o.getVariavel().getNomeVariavel())){
				posicaoVariavel = j;
				j = rep.getListaVariaveis().size();
			}
		}



		// colocando a variavel a ser modificada na lista de variaveis antigas e substitui
		//o valor antigo pelo novo na lista de variaveis atuais
		if(o.getNomeOperacao().equals("Write")&& motivoDaEscrita == 1){
			rep.ValoresAntigosVariaveis(rep.getListaVariaveis().get(posicaoVariavel));
			rep.getListaVariaveis().get(posicaoVariavel).setValor(""+o.getValorNovo());

			retorno =  nomeTransacao+" "+o.getNomeOperacao()+"_lock "+o.getVariavel().getNomeVariavel();

			this.listaOperacoesFinal.add(retorno); // Lista Suja
			this.listaOperacoesOficial.add(retorno); // Lista Oficial

			retorno = nomeTransacao+" "+o.getNomeOperacao()+"_item "+o.getVariavel().getNomeVariavel()+" "+o.getValorAntigo()+" "+o.getValorNovo(); 

			this.listaOperacoesFinal.add(retorno); // Lista Suja
			this.listaOperacoesOficial.add(retorno); // Lista Oficial


		}else if(o.getNomeOperacao().equals("Write")&& motivoDaEscrita == 3){

			rep.ValoresAntigosVariaveis(rep.getListaVariaveis().get(posicaoVariavel));
			rep.getListaVariaveis().get(posicaoVariavel).setValor(""+o.getValorNovo());

			retorno = nomeTransacao+" "+o.getNomeOperacao()+"_item "+o.getVariavel().getNomeVariavel()+" "+o.getValorAntigo()+" "+o.getValorNovo();  
			this.listaOperacoesFinal.add(retorno); // Lista Suja
			this.listaOperacoesOficial.add(retorno); // Lista Oficial

		}else if(o.getNomeOperacao().equals("Write")&& motivoDaEscrita == 2){

			///nao foi feito a escrito pois ja existia alguem bloqueando faz algo

		}else if(o.getNomeOperacao().equals("Read")&& motivoDaEscrita == 1){

			retorno =  nomeTransacao+" "+o.getNomeOperacao()+"_lock "+o.getVariavel().getNomeVariavel(); 
			this.listaOperacoesFinal.add(retorno); // Lista Suja
			this.listaOperacoesOficial.add(retorno); // Lista Oficial

			retorno = nomeTransacao+" "+o.getNomeOperacao()+"_item "+o.getVariavel().getNomeVariavel()+" "+o.getValorAntigo()+" "+o.getValorNovo(); 

			this.listaOperacoesFinal.add(retorno); // Lista Suja
			this.listaOperacoesOficial.add(retorno); // Lista Oficial


		}else if(o.getNomeOperacao().equals("Read")&& motivoDaEscrita == 3){
			retorno = nomeTransacao+" "+o.getNomeOperacao()+"_item "+o.getVariavel().getNomeVariavel()+" "+o.getValorAntigo()+" "+o.getValorNovo();  
			this.listaOperacoesFinal.add(retorno); // Lista Suja
			this.listaOperacoesOficial.add(retorno); // Lista Oficial

		}else if(o.getNomeOperacao().equals("Read")&& motivoDaEscrita == 2){

			///nao foi feito a leitura pois ja existia alguem bloqueando faz algo

		}else if(o.getNomeOperacao().equals("Begin")||o.getNomeOperacao().equals("End")||o.getNomeOperacao().equals("Commit")){
			retorno = nomeTransacao+" "+o.getNomeOperacao();  
			this.listaOperacoesFinal.add(retorno); // Lista Suja
			this.listaOperacoesOficial.add(retorno); // Lista Oficial

		}	

	}

	// Verifica se existira algum bloqueio que impede o unlock da operacao que esta dando Read no momento atual na variavel em questao.
	public boolean existeBloqueiosFuturos(ArrayList<Transacao> listaTransacaoAuxiliar, int posicaoTransacaoLista, Operacao operacaoTemp) {

		boolean existeBloqueioFuturo = false;		
		ArrayList<Operacao> listaOperacoesRecebidaTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes(); //Operacoes Futuras

		for(int i=0; i < listaOperacoesRecebidaTemp.size(); i++){


			for(int j=0; j < listaTransacaoAuxiliar.size(); j++){


				if(j < listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().size()){
					// Se houver ainda write ou read de alguma variavel que nao foi bloqueada ainda 
					if(!listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().get(j).getVariavel().getNomeVariavel().equals(listaOperacoesRecebidaTemp.get(i).getVariavel().getNomeVariavel())){
						existeBloqueioFuturo = true;
					}else{
						//Se houver write de alguma variavel que soh deu read(ou seja, ainda tera uma promocao)
						if(listaTransacaoAuxiliar.get(posicaoTransacaoLista).getListaOperacoes().get(j).getNomeOperacao().equals("Read") && listaOperacoesRecebidaTemp.get(i).getNomeOperacao().equals("Write")){
							existeBloqueioFuturo = true;
						}

					}

				}

			}

		}

		// Se houver algum read dessa variavel em questao que esta dando read agora
		for(int z=0; z < listaOperacoesRecebidaTemp.size(); z++){

			if(listaOperacoesRecebidaTemp.get(z).getVariavel().getNomeVariavel().equals(operacaoTemp.getVariavel().getNomeVariavel())){

				if(listaOperacoesRecebidaTemp.get(z).getNomeOperacao().equals("Read")){
					existeBloqueioFuturo = true;
				}

			}

		}

		return existeBloqueioFuturo;
	}

	public ArrayList<String> getListaOperacoesFinal() {
		return listaOperacoesFinal;
	}

	public void setListaOperacoesFinal(ArrayList<String> listaOperacoesFinal) {
		this.listaOperacoesFinal = listaOperacoesFinal;
	}

	public ArrayList<String> getListaOperacoesOficial() {
		return listaOperacoesOficial;
	}

	public void setListaOperacoesOficial(ArrayList<String> listaOperacoesOficial) {
		this.listaOperacoesOficial = listaOperacoesOficial;
	}

	public ArrayList<Transacao> getListaTransacoesRecebida() {
		return listaTransacoesRecebida;
	}

	public void setListaTransacoesRecebida(
			ArrayList<Transacao> listaTransacoesRecebida) {
		this.listaTransacoesRecebida = listaTransacoesRecebida;
	}



}
