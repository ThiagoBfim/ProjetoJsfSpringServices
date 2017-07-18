package br.net.mirante.exercicio1;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "cotacao")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CreateCotacao");
		wsdl11Definition.setLocationUri("/ws/cotacao");
		wsdl11Definition.setTargetNamespace("http://mirante/soap/resultado");
		wsdl11Definition.setSchema(countriesSchema);
		return wsdl11Definition;
	}


	@Bean(name = "resultado")
	public DefaultWsdl11Definition defaultWsdl11Definition2(XsdSchema resultadoSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("CreateResultado");
		wsdl11Definition.setLocationUri("/ws/resultado");
		wsdl11Definition.setTargetNamespace("http://mirante/soap/resultado");
		wsdl11Definition.setSchema(resultadoSchema);
		return wsdl11Definition;
	}


	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("createCotacao.xsd"));
	}
	
	@Bean
	public XsdSchema resultadoSchema() {
		return new SimpleXsdSchema(new ClassPathResource("createResultado.xsd"));
	}
}
