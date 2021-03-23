package br.com.apizup.proposta.carteiras;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.apizup.proposta.cartao.Cartao;

@Entity
public class Carteira {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	private String email;
	
	private LocalDateTime associadaEm;
	
	@Enumerated(EnumType.STRING)
    private TipoCarteiraEnum emissor;
	
	@ManyToOne
    private Cartao cartao;
	
	@Deprecated
    public Carteira() {
    }
	
	

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getAssociadaEm() {
		return associadaEm;
	}

	public TipoCarteiraEnum getEmissor() {
		return emissor;
	}



	public Carteira(Long id, @NotBlank @Email String email, LocalDateTime associadaEm, TipoCarteiraEnum emissor,
			Cartao cartao) {
		super();
		this.id = id;
		this.email = email;
		this.associadaEm = associadaEm;
		this.emissor = emissor;
		this.cartao = cartao;
	}
	
	
	
}
