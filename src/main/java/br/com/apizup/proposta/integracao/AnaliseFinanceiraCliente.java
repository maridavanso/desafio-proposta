package br.com.apizup.proposta.integracao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "analiseFinanceiraCliente", url = "http://localhost:9999/")
public interface AnaliseFinanceiraCliente {
	
	@PostMapping("/api/solicitacao")
	public EnviaParaAnaliseResponse enviaParaAnalise (@RequestBody EnviaParaAnaliseRequest request);

}
