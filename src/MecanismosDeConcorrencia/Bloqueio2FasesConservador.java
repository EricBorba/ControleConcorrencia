package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Operacao;
import GerenciaDeDados.Transacao;


public class Bloqueio2FasesConservador {

	
	ArrayList<Transacao> listaTransacoesRecebida;
	ArrayList<Operacao> listaOperacoesFinal;
		
	/**
	 * Parametros utilizados para dizer qual associacao sera feita.
	 * **/
	String tipoDeLock;
	String tipoTratamentoDeadlock;
	
	
	public Bloqueio2FasesConservador(ArrayList<Transacao> listaTransacoesRecebida, String tipoDeLock, String tipoTratamentoDeadLock){
		this.listaTransacoesRecebida = listaTransacoesRecebida;
		this.tipoDeLock = tipoDeLock;
		this.tipoTratamentoDeadlock = tipoTratamentoDeadLock;
		this.listaOperacoesFinal = new ArrayList<Operacao>();
	}	
	
	
	/**
	 * Bloqueia todos as variaveis que ira utilizar e vai liberando os bloqueios "exclusivos" aos poucos.
	 * **/
	public ArrayList<Operacao> executar(){
		
		int quantidadeTransacoes = this.listaTransacoesRecebida.size();
		int quantidadeOperacoesRestantes = quantidadeTotalOperacoes(this.listaTransacoesRecebida);
		int posicaoTransacaoLista=0;   // Para saber qual transacao está a ser executada no momento
		ArrayList<Integer> posicaoOperacaoTransacao = criarPosicoesOperacao(this.listaTransacoesRecebida); // Para saber qual operacao será executada dentro da lista de operacoes de uma transacao, a posicao no array é equivalente a posicao da transacao na lista de transacoes.
		boolean conseguiuExecutar = false;		
		bloquearTudo(); // Realiza o bloqueio de todas as variaveis necessárias para a execução das operações.(característica do conservador ).
		
		if(tipoDeLock.equals("lock")){
			if(tipoTratamentoDeadlock.equals(null)){
								
				//Polling criado para ficar alternando entre as transacoes e realizando( se possível ) uma operacao de cada transacao por vez.
				while(posicaoTransacaoLista <= quantidadeTransacoes){
										
					// Porque se fossem iguais iria tentar acessar uma posicao no array que não existe.
					if(posicaoTransacaoLista < quantidadeTransacoes){
						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que está na vez.
						Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(posicaoOperacaoTransacao.get(posicaoTransacaoLista));
						
						
						//ArrayList<Operacao> listaOperacoesAuxiliar = new ArrayList<Operacao>(); // Utilizada para auxiliar na verificação das operações restantes, pra ser passado como parametro e verificar se existe algo que necessite de um bloqueio.
						//listaOperacoesAuxiliar = transacaoTemp.getListaOperacoes();
						
						conseguiuExecutar = executarOperacao(transacaoTemp, operacaoTemp); // Tenta executar a operacao
						
						//Se foi possivel de executar
						if(conseguiuExecutar){
							
							this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes().remove(operacaoTemp);//Remove a operacao que foi realizada com sucesso, ou seja, fica apenas com as restantes.
							ArrayList<Operacao> listaOperacoesAuxiliar = this.listaTransacoesRecebida.get(posicaoTransacaoLista).getListaOperacoes();							
							listaOperacoesFinal.add(operacaoTemp);//Adiciona a operacao atual em uma lista que sera o retorno do metodo todo, ou seja, uma lista com a ordem correta de execucao.
							posicaoOperacaoTransacao.set(posicaoTransacaoLista, (posicaoOperacaoTransacao.get(posicaoTransacaoLista)+1));//Passar para a proxima operacao dentro da lista de operacoes de uma tranasacao
							quantidadeOperacoesRestantes = quantidadeOperacoesRestantes - 1;
							
							
							//Variavel especifica pq ele pode ter dado um read em determinada variavel, mas ainda vai dar um write, logo não poderia desbloquear.
							if((!existeBloqueiosFuturosVariavelEspecifica(listaOperacoesAuxiliar)) && (!existeBloqueiosFuturos(listaOperacoesAuxiliar)) ){
								executarUnlock(transacaoTemp, operacaoTemp); //Se não existe bloqueios futuros é passado como parâmetro a mesma operação que foi executada, poisela contém a variável que já pode ser desbloqueada.
							
							}							
							
						}						
					
					}					
					
					if(quantidadeOperacoesRestantes == 0){
						posicaoTransacaoLista = (this.listaTransacoesRecebida.size() + 1); //Para encerrar o while e consequentemente o método.
					}else if (posicaoTransacaoLista < quantidadeTransacoes){
						posicaoTransacaoLista++; // Para executar a operação da próxima transação.
					}else{
						posicaoTransacaoLista = 0; // Para depois de executar a operacao da ultima transacao retornar para a primeira.
					}
				}
				
			}else if(tipoTratamentoDeadlock.equals("waitdie")){
				
			}else if(tipoTratamentoDeadlock.equals("woundwait")){
				
			}
			
		}else if(tipoDeLock.equals("lockMultiplo")){
			if(tipoTratamentoDeadlock.equals(null)){
				
				//Polling criado para ficar alternando entre as transacoes e realizando( se possível ) uma operacao de cada transacao por vez.
				while(posicaoTransacaoLista <= quantidadeTransacoes){
										
					// Porque se fossem iguais iria tentar acessar uma posicao no array que não existe.
					if(posicaoTransacaoLista < quantidadeTransacoes){
						
						Transacao transacaoTemp = this.listaTransacoesRecebida.get(posicaoTransacaoLista); //Seleciona a transacao que está na vez.
						Operacao operacaoTemp = transacaoTemp.getListaOperacoes().get(posicaoOperacaoTransacao.get(posicaoTransacaoLista));
						
						
						ArrayList<Operacao> listaOperacoesAuxiliar = new ArrayList<Operacao>(); // Utilizada para auxiliar na verificação das operações restantes, pra ser passado como parametro e verificar se existe algo que necessite de um bloqueio.
						listaOperacoesAuxiliar = transacaoTemp.getListaOperacoes();
						
						
					
					
					}					
					
					if(quantidadeOperacoesRestantes == 0){
						posicaoTransacaoLista = (this.listaTransacoesRecebida.size() + 1); //Para encerrar o while e consequentemente o método.
					}else if (posicaoTransacaoLista < quantidadeTransacoes){
						posicaoTransacaoLista++; // Para executar a operação da próxima transação.
					}else{
						posicaoTransacaoLista = 0; // Para depois de executar a operacao da ultima transacao retornar para a primeira.
					}
				}
				
			}else if(tipoTratamentoDeadlock.equals("waitdie")){
				
			}else if(tipoTratamentoDeadlock.equals("woundwait")){
				
			}
		}
		return listaOperacoesFinal;
	}
	
	
	private boolean existeBloqueiosFuturos(ArrayList<Operacao> listaOperacoes) {

		boolean existeBloqueioFuturo = false;
		for(int i=0; i<listaOperacoes.size();i++){
			
			if(listaOperacoes.get(i).getNomeOperacao().equals("Read") || listaOperacoes.get(i).getNomeOperacao().equals("Write")){
				existeBloqueioFuturo = true;
			}
		}
		
		return existeBloqueioFuturo;
	}


	private void bloquearTudo() {
		// TODO Auto-generated method stub
		
	}
	
	// Verifica se ainda existe alguma operacao de write ou read dentro da lista de operacoes passada como parametro.
	public boolean existeBloqueiosFuturosVariavelEspecifica(ArrayList<Operacao> listaOperacoes){
		
		boolean existeBloqueioFuturo = false;
		for(int i=0; i<listaOperacoes.size();i++){
			
			if(listaOperacoes.get(i).getNomeOperacao().equals("Read") || listaOperacoes.get(i).getNomeOperacao().equals("Write")){
				existeBloqueioFuturo = true;
			}
		}
		
		return existeBloqueioFuturo;
	}


	//Retorna a quantidade de operacoes totais a serem realizadas por todas as transacoes
	private int quantidadeTotalOperacoes(ArrayList<Transacao> listaTransacoesRecebida2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//Retorna uma lista de inteiros que será utilizada para sabermos o indice atual de uma lista de operacoes.
	//Recebe como parâmetro a lista de transacoes apenas para saber quantos índices serão necessários, afinal a quantidade
	//de transacoes é a mesma quantidade de listas de operacoes existentes.
	private ArrayList<Integer> criarPosicoesOperacao(ArrayList<Transacao> listaTransacoesRecebida2) {
		ArrayList<Integer> listaIndicesOperacao = new ArrayList<Integer>();
		
		for(int i=0; i < listaTransacoesRecebida2.size(); i++){
			listaIndicesOperacao.add(i, 0);			
		}
		
		return listaIndicesOperacao;
	}
	
	private boolean executarOperacao(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private void executarUnlock(Transacao transacaoTemp, Operacao operacaoTemp) {
		// TODO Auto-generated method stub
		
	}
	
	
}
