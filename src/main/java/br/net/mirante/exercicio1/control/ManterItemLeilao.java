package br.net.mirante.exercicio1.control;

import java.time.LocalDateTime;
import java.util.List;

import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.net.mirante.exercicio1.modelo.Cotacao;
import br.net.mirante.exercicio1.modelo.ItemLeilao;
import br.net.mirante.exercicio1.service.ItemLeilaoServiceImpl;

@SuppressWarnings({ "rawtypes" })
@RequestScoped
@Component
public class ManterItemLeilao {

	private ItemLeilao itemLeilao = new ItemLeilao();

	private Cotacao cotacao = new Cotacao();

	private DataModel listaItemLeilaos;

	@Autowired
	private ItemLeilaoServiceImpl itemLeilaoServiceImpl;

	public String salvarDadosItemLeilao() {
		itemLeilaoServiceImpl.salvarOuAlterar(itemLeilao);
		itemLeilao = new ItemLeilao();
		return "listarItemLeilao.xhtml?faces-redirect=true";
	}

	@SuppressWarnings("unchecked")
	public DataModel getListarItemLeilaos() {
		List<ItemLeilao> lista = itemLeilaoServiceImpl.listar();
		listaItemLeilaos = new ListDataModel(lista);
		return listaItemLeilaos;
	}

	public String prepararAlterarItemLeilao() {
		itemLeilao = (ItemLeilao) (listaItemLeilaos.getRowData());
		return "salvarItemLeilao.xhtml";
	}

	public String excluirItemLeilao() {
		ItemLeilao itemLeilao = (ItemLeilao) (listaItemLeilaos.getRowData());
		itemLeilaoServiceImpl.remover(itemLeilao);
		return "listarItemLeilao.xhtml";
	}

	public String realizarLance() {
		cotacao.setHoraLance(LocalDateTime.now());
		itemLeilaoServiceImpl.salvarOuAlterar(itemLeilao);
		return "listarItemLeilao.xhtml";

	}

	public ItemLeilao getItemLeilao() {
		return itemLeilao;
	}

	public void setItemLeilao(ItemLeilao itemLeilao) {
		this.itemLeilao = itemLeilao;
	}

	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}

}
