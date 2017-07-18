package br.net.mirante.exercicio1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.mirante.exercicio1.modelo.ItemLeilao;
import br.net.mirante.exercicio1.repository.ItemLeilaoRepository;

@Service
public class ItemLeilaoServiceImpl {

	@Autowired
	private ItemLeilaoRepository itemLeilaoRepository;

	@Transactional
	public void salvarOuAlterar(ItemLeilao itemLeilao) {
		itemLeilaoRepository.save(itemLeilao);
	}

	@Transactional
	public ItemLeilao carregar(Integer id) {
		return itemLeilaoRepository.findOne(id);
	}

	@Transactional
	public void remover(ItemLeilao itemLeilao) {
		itemLeilaoRepository.delete(itemLeilao);
	}

	@Transactional
	public List<ItemLeilao> listar() {
		return (List<ItemLeilao>) itemLeilaoRepository.findAll();
	}
}
