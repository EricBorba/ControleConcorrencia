package MecanismosDeConcorrencia;

import java.util.ArrayList;

import GerenciaDeDados.Repositorio;

public class LockBinario {
	
	ArrayList<Bloqueio> listaDeBloqueios;
	String nomeVariavel;
	
	public LockBinario(String nomeVariavelnovo, ArrayList<Bloqueio> listaDeBloqueiosNovo){
		
		this.listaDeBloqueios = listaDeBloqueiosNovo;
		this.nomeVariavel = nomeVariavelnovo;
		
	}	

	public boolean podeBloquear(){
	
		boolean bloqueado = false;
		int posicao = 0;
		for(int i = 0; i < this.listaDeBloqueios.size(); i++){
			if(this.listaDeBloqueios.get(i).getIdvariavel().equals(this.nomeVariavel) == true){
				bloqueado = true;
			}

		}	
		
		return bloqueado;
		
	}
	
	////////////// continuar
	
	
	
}
