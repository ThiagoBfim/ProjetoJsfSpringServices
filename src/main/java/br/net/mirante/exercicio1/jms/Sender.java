package br.net.mirante.exercicio1.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String destination, Integer codigo) {
		LOGGER.info("sending message='{}' to destination='{}'", codigo, destination);
		jmsTemplate.convertAndSend(destination, codigo);
	}

	public void sendCotacao(String destination, CotacaoRequestJms cotacaoRequestJms) {
		LOGGER.info("sending message='{}' to destination='{}'", cotacaoRequestJms, destination);
		jmsTemplate.convertAndSend(destination, cotacaoRequestJms);
	}
}
