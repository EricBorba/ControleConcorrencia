package GerenciaDeDados;

public class BloqueioLockMultiplo {

	String NomeTransacao;
	String NomeVariavel;
	String modoBloqueio;
	
	public BloqueioLockMultiplo(String NomeTransacaoNova,String NomeVariavelNova,String modoBloqueioNovo){

		this.NomeTransacao = NomeTransacaoNova;
		this.NomeVariavel = NomeVariavelNova;
		this.modoBloqueio = modoBloqueioNovo;		
		this.modoBloqueio = modoBloqueioNovo;
		
	}

	
	public String getNomeTransacao() {
		return NomeTransacao;
	}

	public void setNomeTransacao(String nomeTransacao) {
		NomeTransacao = nomeTransacao;
	}

	public String getNomeVariavel() {
		return NomeVariavel;
	}

	public void setNomeVariavel(String nomeVariavel) {
		NomeVariavel = nomeVariavel;
	}

	public String getModoBloqueio() {
		return modoBloqueio;
	}

	public void setModoBloqueio(String modoBloqueio) {
		this.modoBloqueio = modoBloqueio;
	}


}
