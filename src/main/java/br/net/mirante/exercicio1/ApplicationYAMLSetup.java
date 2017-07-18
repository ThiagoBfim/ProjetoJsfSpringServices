package br.net.mirante.exercicio1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import br.net.mirante.exercicio1.service.CotacaoServiceImpl;

@Component
@ConfigurationProperties("connection")
public class ApplicationYAMLSetup {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationYAMLSetup.class);

	@Autowired
	private CotacaoServiceImpl cotacaoServiceImpl;

	private Integer codigoLeilao;

	private String resultado;

	public Integer getCodigoLeilao() {
		return codigoLeilao;
	}

	public void setCodigoLeilao(Integer codigoLeilao) {
		this.codigoLeilao = codigoLeilao;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String createResult() {
		LOG.info("Initialized codigo = [{}]", codigoLeilao);
		String resposta = cotacaoServiceImpl.carregarRespostaByLeilao(codigoLeilao);
		this.resultado = resposta;
		return resposta;
	}

}
