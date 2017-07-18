package br.net.mirante.exercicio1.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import br.net.mirante.exercicio1.service.CotacaoServiceImpl;

@Endpoint
public class CotacaoEndPoint {
	private static final String NAMESPACE_URI = "http://mirante/soap/resultado";

	private CotacaoServiceImpl cotacaoServiceImpl;

	@Autowired
	public CotacaoEndPoint(CotacaoServiceImpl cotacaoServiceImpl) {
		this.cotacaoServiceImpl = cotacaoServiceImpl;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "leilaoRequest")
	@ResponsePayload
	public GetCotacaoResponse getCotacao(@RequestPayload LeilaoRequest leilaoRequest) {
		GetCotacaoResponse getCountryResponse = new GetCotacaoResponse();
		getCountryResponse.setResposta(cotacaoServiceImpl.criarCotacaoByWbs(leilaoRequest));
		return getCountryResponse;
		// return cotacaoServiceImpl.criarCotacaoByWbs(leilaoRequest);

	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "resultadoRequest")
	@ResponsePayload
	public GetCotacaoResponse getResultado(@RequestPayload ResultadoRequest resultadoRequest) {
		GetCotacaoResponse getCountryResponse = new GetCotacaoResponse();
		String resposta = cotacaoServiceImpl.carregarRespostaByLeilao(resultadoRequest.getCodigoLeilao());
		getCountryResponse.setResposta(resposta);
		return getCountryResponse;

	}

}
