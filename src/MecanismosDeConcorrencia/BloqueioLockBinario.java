package MecanismosDeConcorrencia;

public class BloqueioLockBinario {
	String IdTransacao;
	String Idvariavel;

	public BloqueioLockBinario(String NomeTransacao,String nomeVariavel){

		this.IdTransacao = nomeVariavel;
		this.Idvariavel = nomeVariavel;
	
	}

	public String getIdTransacao() {
		return IdTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		IdTransacao = idTransacao;
	}

		public String getIdvariavel() {
		return Idvariavel;
	}

	public void setIdvariavel(String idvariavel) {
		Idvariavel = idvariavel;
	}
    
		

}
