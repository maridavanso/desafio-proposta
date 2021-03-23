package br.com.apizup.proposta.carteiras;

import java.net.URI;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;



import br.com.apizup.proposta.cartao.Cartao;
import br.com.apizup.proposta.cartao.CartaoResponse;
import br.com.apizup.proposta.cartao.CartaoRouter;
import feign.FeignException;

@RestController
@RequestMapping("/carteiras/{id}")
public class CarteiraController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	CartaoRouter cartaoRouter;

	@Transactional
	@PostMapping
	public ResponseEntity<?> criar(@PathVariable Long id, @RequestBody @Valid CarteiraRequest request,
			UriComponentsBuilder uriBuilder) {

		Cartao cartao = Optional.ofNullable(manager.find(Cartao.class, id))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id de cartão não encontrado"));
		try {

			cartaoRouter.criaCarteira(cartao.getNumeroCartao(), request);
			
			CartaoResponse response = cartaoRouter.buscaCartaoPorId(cartao.getNumeroCartao());

			Carteira carteira = response.getUltimaCarteira().toModel(cartao);

			cartao.addCarteira(carteira);

			manager.merge(cartao);

			URI uri = uriBuilder.path("/carteiras/{id}").buildAndExpand(carteira.getId()).toUri();

			return ResponseEntity.created(uri).build();
		} catch (FeignException e) {
			return ResponseEntity.unprocessableEntity().build();
		}

	}

}
