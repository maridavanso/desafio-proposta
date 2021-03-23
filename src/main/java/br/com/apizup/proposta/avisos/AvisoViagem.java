package br.com.apizup.proposta.avisos;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String destino;
	
	private LocalDateTime validoAte;
	
	private String ipCliente;

	private String userAgent;

	private LocalDateTime criadoEm = LocalDateTime.now();
	
	@Deprecated
    public AvisoViagem() {
    }
}
