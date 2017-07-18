package br.net.mirante.exercicio1.control;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import br.net.mirante.exercicio1.ApplicationYAMLSetup;
import br.net.mirante.exercicio1.jms.CotacaoRequestJms;

@Component
@RequestScoped
public class ManterYAMLLeilao {

	private CotacaoRequestJms cotacao = new CotacaoRequestJms();

	@Resource
	private ApplicationYAMLSetup applicationYAMLSetup;

	@RequestMapping("/YAML")
	public void resultadoYAML(Integer codigo) {
		applicationYAMLSetup.setCodigoLeilao(codigo);
		FacesContext.getCurrentInstance().addMessage("message", new FacesMessage(applicationYAMLSetup.createResult()));
	}

	public CotacaoRequestJms getCotacao() {
		return cotacao;
	}

	public void setCotacao(CotacaoRequestJms cotacao) {
		this.cotacao = cotacao;
	}

}
