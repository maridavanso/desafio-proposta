package br.com.apizup.proposta.cadastrobiometria;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping("/{idCartao}")
	@Transactional
	public ResponseEntity<BiometriaResponse> cria(@RequestBody @Valid BiometriaRequest form,
			@PathVariable("idCartao") Long id, UriComponentsBuilder uriBuilder) {

		Biometria biometria = form.toModel(id, manager);

		manager.persist(biometria);

		URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();

		return ResponseEntity.created(uri).body(new BiometriaResponse(biometria));

	}
}
