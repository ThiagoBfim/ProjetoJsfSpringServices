package br.net.mirante.exercicio1.control;

import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.mirante.exercicio1.modelo.Cliente;
import br.net.mirante.exercicio1.service.ClienteServiceImpl;

@SuppressWarnings({ "rawtypes" })
@RequestScoped
@Component
public class ManterCliente {

	private Cliente cliente = new Cliente();

	private DataModel listaClientes;

	@Autowired
	private ClienteServiceImpl clienteServiceImpl;

	public String salvarDadosClientes() {
		clienteServiceImpl.salvarOuAlterar(cliente);
		cliente = new Cliente();
		return "listarCliente.xhtml?faces-redirect=true";
	}

	@SuppressWarnings("unchecked")
	public DataModel getListarClientes() {
		List<Cliente> lista = clienteServiceImpl.listar();
		listaClientes = new ListDataModel(lista);
		return listaClientes;
	}

	public String prepararAlterarCliente() {
		cliente = (Cliente) (listaClientes.getRowData());
		return "salvarCliente.xhtml";
	}


	public String excluirCliente() {
		Cliente cliente = (Cliente) (listaClientes.getRowData());
		clienteServiceImpl.remover(cliente);
		return "listarCliente.xhtml";

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
