package br.com.apizup.proposta.carteiras;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.apizup.proposta.cartao.Cartao;

public class CarteiraResponse {

	private Long id;
	private @NotBlank @Email String email;
	private String associadaEm;
	private TipoCarteiraEnum emissor;

	public Carteira toModel(Cartao cartao) {
        return new Carteira(id, email, LocalDateTime.parse(associadaEm), emissor, cartao);
    }
	
	

}
