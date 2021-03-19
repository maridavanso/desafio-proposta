package br.com.apizup.proposta.cadastrobiometria;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import br.com.apizup.proposta.cartao.Cartao;

public class BiometriaRequest {

    @Size(max = 1024)
    @NotBlank 
    private String digital;

    @Deprecated
    public BiometriaRequest() {
    }

    public BiometriaRequest(String digital) {
        this.digital = digital;
    }

    public String getDigital() {
        return digital;
    }

    public Biometria toModel(Long id, EntityManager manager) {

		
    	  Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, id)).orElseThrow(() -> new ResponseStatusException(
                          HttpStatus.NOT_FOUND,
                          "ID do cartão não foi encontrado"
                  ));
		 

        return new Biometria(digital,  cartao);

    }
}