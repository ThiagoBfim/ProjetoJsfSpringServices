package br.net.mirante.exercicio1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.mirante.exercicio1.modelo.Cliente;
import br.net.mirante.exercicio1.repository.ClienteRepository;

@Service
public class ClienteServiceImpl {

	@Autowired
	private ClienteRepository clienteDAO;

	@Transactional
	public void salvarOuAlterar(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	@Transactional
	public Cliente carregar(Integer id) {
		return clienteDAO.findOne(id);
	}

	@Transactional
	public void remover(Cliente cliente) {
		clienteDAO.delete(cliente);
	}

	@Transactional
	public List<Cliente> listar() {
		return (List<Cliente>) clienteDAO.findAll();
	}

	@Transactional
	public Optional<Cliente> findByNomeAndSenha(String nome, String senha) {
		return clienteDAO.findByNomeAndSenha(nome, senha);

	}

}
