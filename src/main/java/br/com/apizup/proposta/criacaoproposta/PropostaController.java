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

import br.com.apizup.proposta.integracao.AnaliseFinanceiraCliente;
import br.com.apizup.proposta.integracao.EnviaParaAnaliseRequest;
import br.com.apizup.proposta.integracao.EnviaParaAnaliseResponse;
import feign.FeignException;

@RestController
public class PropostaController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private AnaliseFinanceiraCliente analiseFinanceiraCliente;

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

		EnviaParaAnaliseRequest req = new EnviaParaAnaliseRequest(proposta);
		
		PropostaStatus status = null;
		try {
			
			EnviaParaAnaliseResponse response = analiseFinanceiraCliente.enviaParaAnalise(req);
			status = response.toModel();
			
		} catch (FeignException.UnprocessableEntity e) {
			status = PropostaStatus.NAO_ELEGIVEL;
			
		}
		
		proposta.atualizaStatus(status);
		
		URI location = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(location).build();

	}
}