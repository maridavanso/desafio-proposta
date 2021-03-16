package br.com.apizup.proposta.criacaoproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class PropostaController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PropostaRepository propostaRepository;

	@Transactional
	@PostMapping(value = "/propostas")
	public ResponseEntity<?> cria(@RequestBody @Valid NovaPropostaRequest request,
			UriComponentsBuilder uriComponentsBuilder) {

		String documento = request.getDocumento();
		if (propostaRepository.existsByDocumento(documento)) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Essa proposta já existe");
		}

		Proposta proposta = request.paraProposta();
		propostaRepository.save(proposta);

		URI location = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
}