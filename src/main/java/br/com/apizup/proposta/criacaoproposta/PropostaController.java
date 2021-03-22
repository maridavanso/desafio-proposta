package br.com.apizup.proposta.criacaoproposta;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.apizup.proposta.integracao.AnaliseFinanceiraCliente;
import br.com.apizup.proposta.integracao.EnviaParaAnaliseRequest;
import br.com.apizup.proposta.integracao.EnviaParaAnaliseResponse;
import feign.FeignException;
import feign.Logger;

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

		PropostaStatus status = enviaParaAnalise(proposta);
		proposta.atualizaStatus(status);
		

		URI location = uriComponentsBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
		return ResponseEntity.created(location).body(new NovaPropostaResponse(proposta));

	}
	
	public BodyBuilder obterProposta(){
	  
	    return ResponseEntity.status(HttpStatus.NOT_FOUND);
	}


	private PropostaStatus enviaParaAnalise(Proposta proposta) {
		try {
			EnviaParaAnaliseRequest req = new EnviaParaAnaliseRequest(proposta);
			EnviaParaAnaliseResponse response = analiseFinanceiraCliente.enviaParaAnalise(req);
			return response.toModel();
		} catch (FeignException.UnprocessableEntity e) {
			return PropostaStatus.NAO_ELEGIVEL;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Um erro inesperado aconteceu");
		}
	}

}