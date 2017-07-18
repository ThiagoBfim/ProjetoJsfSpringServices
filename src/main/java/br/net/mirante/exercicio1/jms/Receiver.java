package br.net.mirante.exercicio1.jms;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import br.net.mirante.exercicio1.service.CotacaoServiceImpl;

@Component
public class Receiver {

	@Autowired
	private CotacaoServiceImpl cotacaoServiceImpl;

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	@JmsListener(destination = "cotacaoBase", containerFactory = "myFactory")
	public void receiveMessage(CotacaoRequestJms cotacaoRequestJms) {

		cotacaoServiceImpl.salvarOuAlterar(cotacaoRequestJms.toEntity());
	}

	@JmsListener(destination = "resultadoBase", containerFactory = "myFactory")
	public void receiveMessageResult(Integer codigo) {
		String resposta = cotacaoServiceImpl.carregarRespostaByLeilao(codigo);
		System.out.println(resposta);

	}

}
