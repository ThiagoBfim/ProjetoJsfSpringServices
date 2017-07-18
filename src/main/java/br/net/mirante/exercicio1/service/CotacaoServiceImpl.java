package br.net.mirante.exercicio1.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.mirante.exercicio1.modelo.Cliente;
import br.net.mirante.exercicio1.modelo.Cotacao;
import br.net.mirante.exercicio1.modelo.ItemLeilao;
import br.net.mirante.exercicio1.repository.CotacaoRepository;
import br.net.mirante.exercicio1.soap.LeilaoRequest;

@Service
public class CotacaoServiceImpl {

	@Autowired
	private CotacaoRepository cotacaoRepository;

	@Autowired
	private ItemLeilaoServiceImpl itemLeilaoServiceImpl;

	@Autowired
	private ClienteServiceImpl clienteServiceImpl;

	@Transactional
	public void salvarOuAlterar(Cotacao cliente) {
		cotacaoRepository.save(cliente);
	}

	@Transactional
	public Cotacao carregar(Integer id) {
		return cotacaoRepository.findOne(id);
	}

	@Transactional
	public void remover(Cotacao cliente) {
		cotacaoRepository.delete(cliente);
	}

	@Transactional
	public List<Cotacao> listar() {
		return (List<Cotacao>) cotacaoRepository.findAll();
	}

	/**
	 * Metodo para criar a cotacao provinda do Web Service.
	 * 
	 * @param leilaoRequest
	 *            Request do Web Service de cotacao.
	 * @return Retorna uma mensagem com sucesso ou exceção.
	 */
	public String criarCotacaoByWbs(LeilaoRequest leilaoRequest) {
		try {
			Cotacao cotacao = new Cotacao();
			ItemLeilao itemLeilao = itemLeilaoServiceImpl.carregar(leilaoRequest.getCodigoItemLeilao());
			if (itemLeilao != null) {
				cotacao.setItemLeilao(itemLeilao);
				cotacao.setHoraLance(LocalDateTime.now());
				cotacao.setValor(leilaoRequest.getValor());
				Optional<Cliente> cliente = clienteServiceImpl.findByNomeAndSenha(leilaoRequest.getLogin(),
						leilaoRequest.getSenha());
				if (cliente.isPresent()) {
					cotacao.setCliente(cliente.get());
					salvarOuAlterar(cotacao);
					return "Sucesso ao salvar Cotação!";
				} else {
					// RETORNA ERRO
					return "Login ou senha incorretos!";
				}
			} else {
				// RETORNA ERRO
				return "Item de leilão não existe!";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			return "Erro ao preencher algum dos campos!";
		}
	}

	public String carregarRespostaByLeilao(Integer codigoLeilao) {
		Cotacao cotacao = cotacaoRepository.carregarByLeilao(codigoLeilao);
		String resposta;
		if (cotacao == null) {
			resposta = "Não houve nenhum lance para esse item de leilão.";
		} else if (cotacao.getItemLeilao().getDataFimLance().isBefore(LocalDateTime.now())) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			resposta = "O vencedor foi : " + cotacao.getCliente().getNome() + " no valor de : "
					+ cotacao.getValor().toString() + " e o lance aconteceu às: "
					+ cotacao.getHoraLance().format(formatter) + " e o leilão acabou às: "
					+ cotacao.getItemLeilao().getDataFimLance().format(formatter);
		} else {
			resposta = " O leilão ainda nao acabou, hora de termino :"
					+ cotacao.getItemLeilao().getDataFimLance().toString().replaceAll("T", " ");
		}
		return resposta;

	}

}
