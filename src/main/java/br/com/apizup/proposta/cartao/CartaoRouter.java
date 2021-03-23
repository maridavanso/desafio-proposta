package br.com.apizup.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.apizup.proposta.avisos.AvisoViagemRequest;
import br.com.apizup.proposta.avisos.AvisoViagemResponse;
import br.com.apizup.proposta.bloqueiocartao.BloqueioCartaoRequest;
import br.com.apizup.proposta.bloqueiocartao.BloqueioCartaoResponse;

@FeignClient(name = "cartoes", url = "http://localhost:9999/")
public interface CartaoRouter {
	
	 @PostMapping("/api/cartoes/{id}/bloqueios")
	    BloqueioCartaoResponse bloqueioCartao(@PathVariable String id, BloqueioCartaoRequest bloqueioRequest);

	 @GetMapping("/api/cartoes/{id}")
	    CartaoResponse buscaCartaoPorId(@PathVariable String id);
	 
	 @PostMapping("api/cartoes/{id}/avisos")
	    AvisoViagemResponse avisoViagem(@PathVariable String id, AvisoViagemRequest avisoRequest);
}
