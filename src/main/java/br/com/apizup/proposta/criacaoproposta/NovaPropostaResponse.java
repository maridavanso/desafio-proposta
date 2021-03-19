package br.com.apizup.proposta.criacaoproposta;

import java.math.BigDecimal;

import org.hibernate.validator.internal.util.logging.LoggerFactory;

import feign.Logger;

public class NovaPropostaResponse {
	private String documento;
	private String email;
	private String nome;
    private BigDecimal salario;
    private EnderecoRequest endereco;
    private PropostaStatus propostaStatus;
  

	public NovaPropostaResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.salario = proposta.getSalario();
		this.endereco = new EnderecoRequest(proposta.getEndereco());
		this.propostaStatus = proposta.getStatus();
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


	public PropostaStatus getPropostaStatus() {
		return propostaStatus;
	}
    
    
  
}
