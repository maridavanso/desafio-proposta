package br.com.apizup.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.apizup.proposta.bloqueiocartao.BloqueioCartao;
import br.com.apizup.proposta.criacaoproposta.Proposta;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String numeroCartao;

	@OneToOne
	private Proposta proposta;

	@Deprecated
	public Cartao() {
	}

	public String getNumeroCartao() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addBloqueio(BloqueioCartao bloqueioCartao) {
		// TODO Auto-generated method stub
		
	}

}
