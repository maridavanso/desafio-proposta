package br.com.apizup.proposta.criacaoproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;




@RestController
public class PropostaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@PostMapping(value = "/propostas")
	@Transactional
	public ResponseEntity<?> cria( @RequestBody @Valid NovaPropostaRequest request, UriComponentsBuilder uriComponentsBuilder ) {
		
		Proposta proposta = request.paraProposta();
		propostaRepository.save(proposta);
		
		URI location = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri() ;
		return ResponseEntity.created(location).build();
		
	}
}