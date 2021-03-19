package br.com.apizup.proposta.cadastrobiometria;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.apizup.proposta.cartao.Cartao;

@Entity
public class Biometria {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String digital;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

	public Biometria(String digital, Cartao cartao) {
        this.digital = digital;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getDigital() {
        return digital;
    }

    public Cartao getCartao() {
        return cartao;
    }
}


