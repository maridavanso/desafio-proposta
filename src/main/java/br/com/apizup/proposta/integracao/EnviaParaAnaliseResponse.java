package br.com.apizup.proposta.integracao;

import br.com.apizup.proposta.criacaoproposta.PropostaStatus;

public class EnviaParaAnaliseResponse {

	private String idProposta;
	private String resultadoSolicitacao;

	public String getIdProposta() {
		return idProposta;
	}

	public String getResultadoSolicitacao() {
		return resultadoSolicitacao;
	}

	@Override
	public String toString() {
		return "EnviaParaAnaliseResponse [idProposta=" + idProposta + ", resultadoSolicitacao=" + resultadoSolicitacao
				+ "]";
	}

	public PropostaStatus toModel() {
		if ("COM_RESTRICAO".equals(resultadoSolicitacao)) {
			return PropostaStatus.NAO_ELEGIVEL;
		}
		return PropostaStatus.ELEGIVEL;
	}

}
