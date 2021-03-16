package br.com.apizup.proposta.criacaoproposta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String documento;
	private @NotBlank @Email String email;
	private @NotBlank String nome;
	private @NotNull @Positive BigDecimal salario;

	@Embedded
	private Endereco endereco;

	@NotNull
	@Enumerated(EnumType.STRING)
	private PropostaStatus status = PropostaStatus.NAO_ELEGIVEL ;
	private LocalDateTime updateAt = LocalDateTime.now();
	
	@Deprecated
	public Proposta() {

	}

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotNull @Positive BigDecimal salario, @NotNull Endereco endereco) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;
		

	}

	public Long getId() {
		return this.id;
	}

	public PropostaStatus getStatus() {
		return status;
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

	public Endereco getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return "Proposta [id=" + id + ", documento=" + documento + ", email=" + email + ", nome=" + nome + ", salario="
				+ salario + ", endereco=" + endereco + ", status=" + status + "]";
	}
	
	public void atualizaStatus(PropostaStatus status) {
		this.status = status;
		this.updateAt = LocalDateTime.now();
		

	}
	
	

}
