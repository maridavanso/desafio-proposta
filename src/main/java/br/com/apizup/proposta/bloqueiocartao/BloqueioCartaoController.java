package br.com.apizup.proposta.bloqueiocartao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.apizup.proposta.cartao.Cartao;
import br.com.apizup.proposta.cartao.CartaoResponse;
import br.com.apizup.proposta.cartao.CartaoRouter;
import feign.FeignException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestController
public class BloqueioCartaoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	CartaoRouter cartaoRouter;

	@Transactional
	@PostMapping(value = "/bloqueios")
	public ResponseEntity<?> bloqueia(@PathVariable String id, @RequestHeader(HttpHeaders.USER_AGENT) String userAgent,
			@RequestBody @Valid BloqueioCartaoRequest request, HttpServletRequest requestInfo) {

		Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, id)).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "O ID do cartão não foi encontrado"));

		try {
			cartaoRouter.bloqueioCartao(cartao.getNumeroCartao(), new BloqueioCartaoRequest("Proposta"));

			CartaoResponse respostaCartao = cartaoRouter.buscaCartaoPorId(cartao.getNumeroCartao());

			BloqueioCartao bloqueioCartao = respostaCartao.getUltimoBloqueio().toModel();

			bloqueioCartao.setInformacoesDeRequest(requestInfo.getRemoteAddr(), userAgent, cartao);
			cartao.addBloqueio(bloqueioCartao);

			manager.persist(bloqueioCartao);
		} catch (FeignException e) {
			return ResponseEntity.unprocessableEntity().build();
		}

		return ResponseEntity.ok().build();
	}

}
