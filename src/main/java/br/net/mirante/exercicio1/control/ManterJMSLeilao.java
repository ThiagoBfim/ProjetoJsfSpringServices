package br.net.mirante.exercicio1.control;

import java.util.Date;
import java.util.Optional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.mirante.exercicio1.jms.CotacaoRequestJms;
import br.net.mirante.exercicio1.jms.Sender;
import br.net.mirante.exercicio1.modelo.Cliente;
import br.net.mirante.exercicio1.service.ClienteServiceImpl;

@Component
@RequestScoped
public class ManterJMSLeilao {

	private CotacaoRequestJms cotacao = new CotacaoRequestJms();

	@Autowired
	private Sender sender;

	@Autowired
	private ClienteServiceImpl clienteServiceImpl;

	public void realizarLanceJms() {
		cotacao.setHoraLance(new Date());
		Optional<Cliente> cliente = clienteServiceImpl.findByNomeAndSenha(cotacao.getCliente().getNome(),
				cotacao.getCliente().getSenha());
		if (cliente.isPresent()) {
			cotacao.setCliente(cliente.get());
			sender.sendCotacao("cotacaoBase", cotacao);
		} else {
			FacesContext.getCurrentInstance().addMessage("message", new FacesMessage("Erro de validaçao do cliente!"));
		}
		cotacao = new CotacaoRequestJms();

	}

	public void getGanhador(Integer codigo) {
		sender.send("resultadoBase", codigo);
		FacesContext.getCurrentInstance().addMessage("message", new FacesMessage("Resultado impresso no console."));
	}

	public CotacaoRequestJms getCotacao() {
		return cotacao;
	}

	public void setCotacao(CotacaoRequestJms cotacao) {
		this.cotacao = cotacao;
	}

}
