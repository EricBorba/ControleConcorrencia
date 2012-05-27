package MecanismosDeConcorrencia;

public class Bloqueio {
	String IdTransacao;
	String bloqueio;
	String Idvariavel;

	public Bloqueio(String NomeTransacao, String bloqueio, String nomeVariavel){

		this.IdTransacao = nomeVariavel;
		this.Idvariavel = nomeVariavel;
		this.bloqueio = bloqueio;

	}

	public String getIdTransacao() {
		return IdTransacao;
	}

	public void setIdTransacao(String idTransacao) {
		IdTransacao = idTransacao;
	}

	public String getBloqueio() {
		return bloqueio;
	}

	public void setBloqueio(String bloqueio) {
		this.bloqueio = bloqueio;
	}

	public String getIdvariavel() {
		return Idvariavel;
	}

	public void setIdvariavel(String idvariavel) {
		Idvariavel = idvariavel;
	}
    
		

}
