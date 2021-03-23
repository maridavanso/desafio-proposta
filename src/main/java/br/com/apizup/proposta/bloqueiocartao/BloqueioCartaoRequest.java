package br.com.apizup.proposta.bloqueiocartao;

import javax.validation.constraints.NotBlank;

public class BloqueioCartaoRequest {

	@NotBlank
	private String sistemaResponsavel;

	public BloqueioCartaoRequest(String sistemaResponsavel) {
		this.sistemaResponsavel = sistemaResponsavel;
	}

	public String getSistemaResponsavel() {
		return sistemaResponsavel;
	}

	@Override
	public String toString() {
		return "BloqueioCartaoRequest [sistemaResponsavel=" + sistemaResponsavel + "]";
	}

	public BloqueioCartao toModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

