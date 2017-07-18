package br.net.mirante.exercicio1;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.jms.ConnectionFactory;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.annotation.HandlesTypes;

import org.apache.catalina.Context;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.sun.faces.config.FacesInitializer;

@SpringBootApplication
@EnableJms
public class Starter {

	/**
	 * Uma empresa de leilão eletrônico necessita de um sistema que receba
	 * propostas de clientes credenciados para os itens leiloados. CRIAR PERIODO
	 * VALIDADE * Cada item tem um número de série único, e pode receber várias
	 * cotações durante o período de validade do leilão. Uma cotação é dada pelo
	 * número do item, o valor e as credencias de um cliente. O resultado do
	 * leilão é processado somente no seu horário de encerramento em seguida a
	 * empresa leiloeira precisa informar aos seus clientes o vencedor do
	 * leilão, o preço, a data e a hora do lance.
	 * 
	 */
	@Value("${service.port}")
	private static String servicePort;

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}

	@Bean
	@ConditionalOnMissingBean(EmbeddedServletContainerFactory.class)
	public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		tomcat.addContextCustomizers(new TomcatContextCustomizer() {
			@Override
			public void customize(Context context) {
				// register FacesInitializer
				context.addServletContainerInitializer(new FacesInitializer(),
						getServletContainerInitializerHandlesTypes(FacesInitializer.class));

				// register additional mime-types that Spring Boot doesn't
				context.addMimeMapping("eot", "application/vnd.ms-fontobject");
				context.addMimeMapping("ttf", "application/x-font-ttf");
				context.addMimeMapping("woff", "application/x-font-woff");
			}
		});

		return tomcat;
	}

	@SuppressWarnings("rawtypes")
	private Set<Class<?>> getServletContainerInitializerHandlesTypes(
			Class<? extends ServletContainerInitializer> sciClass) {
		HandlesTypes annotation = sciClass.getAnnotation(HandlesTypes.class);
		if (annotation == null) {
			return Collections.emptySet();
		}

		Class[] classesArray = annotation.value();
		Set<Class<?>> classesSet = new HashSet<Class<?>>(classesArray.length);
		for (Class clazz : classesArray) {
			classesSet.add(clazz);
		}

		return classesSet;
	}

	// JMS CONFIG
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		// This provides all boot's default to this factory, including the
		// message converter
		configurer.configure(factory, connectionFactory);
		// You could still override some of Boot's default if necessary.
		return factory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}
}
