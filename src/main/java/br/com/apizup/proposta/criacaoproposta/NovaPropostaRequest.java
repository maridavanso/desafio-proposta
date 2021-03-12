package br.com.apizup.proposta.criacaoproposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


import br.com.apizup.proposta.compartilhado.CpfOuCnpj;

public class NovaPropostaRequest {

	
	@NotBlank
	@CpfOuCnpj
	private String documento;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal salario;
	
	@NotNull
	private EnderecoRequest endereco;

	  @Deprecated
	    public NovaPropostaRequest() {
	    }

	public NovaPropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotNull @Positive BigDecimal salario, @NotNull EnderecoRequest endereco) {
		super();
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}


	public EnderecoRequest getEndereco() {
		return endereco;
	}

	public Proposta paraProposta() {
		return new Proposta(documento, email, nome, salario, endereco.paraEndereco());
	}

}
