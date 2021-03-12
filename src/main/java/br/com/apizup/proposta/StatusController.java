package br.com.apizup.proposta;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
	@GetMapping(value = "/api/test")
	public String test() {
		return "tudo show";
	}

}
