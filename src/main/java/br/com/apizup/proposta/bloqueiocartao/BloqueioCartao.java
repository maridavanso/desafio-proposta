package br.com.apizup.proposta.bloqueiocartao;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import br.com.apizup.proposta.cartao.Cartao;

@Entity
public class BloqueioCartao {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@ManyToOne
	private Cartao cartao;
	
	private LocalDateTime instanteBloqueio;
	
	private String ipCliente;

    private String userAgent;
	
    @Deprecated
    public BloqueioCartao() {
    }

	public BloqueioCartao(String id, Cartao cartao, LocalDateTime instanteBloqueio, String ipCliente, String userAgent) {
		
		this.id = id;
		this.cartao = cartao;
		this.instanteBloqueio = instanteBloqueio;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
	}

	public String getId() {
		return id;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public LocalDateTime getInstanteBloqueio() {
		return instanteBloqueio;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public void setInformacoesDeRequest(String remoteAddr, String userAgent2, Cartao cartao2) {
		
		
	}

	

    
    
	
}
