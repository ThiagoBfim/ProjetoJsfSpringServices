package br.net.mirante.exercicio1.test;

import static org.assertj.core.api.Assertions.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.net.mirante.exercicio1.ApplicationYAMLSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestYAML {
	@LocalServerPort
	private int port = 8090;
	
	@Resource
	private ApplicationYAMLSetup applicationYAMLSetup;
	
	@Test
	public void testResultado() {
		applicationYAMLSetup.setCodigoLeilao(1);
		assertThat(applicationYAMLSetup.createResult()).isNotNull();
	}
}