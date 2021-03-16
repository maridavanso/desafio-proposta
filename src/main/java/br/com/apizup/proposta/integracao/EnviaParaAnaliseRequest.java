package br.com.apizup.proposta.integracao;

import br.com.apizup.proposta.criacaoproposta.Proposta;

public class EnviaParaAnaliseRequest {
	
	private String documento;
	

	private String nome;
	
	
	private String idProposta;

	


	public EnviaParaAnaliseRequest(Proposta proposta) {
		this.documento = proposta.getDocumento() ;
		this.nome = proposta.getNome();
		this.idProposta = proposta.getId().toString();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}

	@Override
	public String toString() {
		return "EnviaParaAnaliseRequest [documento=" + documento + ", nome=" + nome + ", idProposta=" + idProposta
				+ "]";
	}
	
	
	

}
