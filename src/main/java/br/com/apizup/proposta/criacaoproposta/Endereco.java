package br.com.apizup.proposta.criacaoproposta;

import javax.persistence.Embeddable;

import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {

	@NotBlank
	private String logradouro;

	@NotBlank
	private String numero;

	@NotBlank
	private String cep;

	@Deprecated
	public Endereco() {

	}

	public Endereco(@NotBlank String logradouro, @NotBlank String numero, @NotBlank String cep) {

		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;

	}
	
	

}
