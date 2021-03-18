package br.com.apizup.proposta.criacaoproposta;

import javax.validation.constraints.NotBlank;

public class EnderecoRequest {

	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String cep;
	
	@Deprecated
	public EnderecoRequest(Endereco endereco) {

	}

	public EnderecoRequest(Endereco endereco, @NotBlank String logradouro, @NotBlank String numero, @NotBlank String cep) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.cep = cep;
	}
	
	

	

	@Override
	public String toString() {
		return "EnderecoRequest [logradouro=" + logradouro + ", numero=" + numero + ", cep=" + cep + "]";
	}

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