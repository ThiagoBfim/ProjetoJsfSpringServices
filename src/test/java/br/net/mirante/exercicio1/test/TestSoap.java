package br.net.mirante.exercicio1.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import br.net.mirante.exercicio1.soap.LeilaoRequest;
import br.net.mirante.exercicio1.soap.ResultadoRequest;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestSoap {

	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

	@LocalServerPort
	private int port = 8090;

	@Before
	public void init() throws Exception {
		marshaller.setPackagesToScan(ClassUtils.getPackageName(LeilaoRequest.class),
				ClassUtils.getPackageName(ResultadoRequest.class));
		marshaller.afterPropertiesSet();
	}

	@Test
	public void testCotacao() {
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		LeilaoRequest request = new LeilaoRequest();
		request.setCodigoItemLeilao(1);
		request.setLogin("teste");
		request.setSenha("123");
		request.setValor(new BigDecimal(100));
		assertThat(ws.marshalSendAndReceive("http://localhost:" + port + "/ws/cotacao", request)).isNotNull();
	}

	@Test
	public void testResultado() {
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		ResultadoRequest request = new ResultadoRequest();
		request.setCodigoLeilao(1);
		assertThat(ws.marshalSendAndReceive("http://localhost:" + port + "/ws/resposta", request)).isNotNull();
	}
}