package br.com.apizup.proposta.criacaoproposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String cep;
	
	

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public String getCep() {
		return cep;
	}

	public Endereco paraEndereco() {
		return new Endereco(logradouro, numero, cep);
	}


}