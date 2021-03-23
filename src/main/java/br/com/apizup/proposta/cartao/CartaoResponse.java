package br.com.apizup.proposta.cartao;

import java.util.List;

import javax.validation.Valid;

import br.com.apizup.proposta.bloqueiocartao.BloqueioCartaoRequest;
import br.com.apizup.proposta.bloqueiocartao.BloqueioCartaoResponse;
import br.com.apizup.proposta.carteiras.CarteiraResponse;

public class CartaoResponse {
	
	private List<CarteiraResponse> carteiras;

	public @Valid BloqueioCartaoResponse getUltimoBloqueio() {
		// TODO Auto-generated method stub
		return null;
	}

	public CarteiraResponse getUltimaCarteira() {
        return carteiras.get(carteiras.size() - 1);
    }

}
