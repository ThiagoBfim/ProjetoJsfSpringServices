package br.net.mirante.exercicio1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.net.mirante.exercicio1.modelo.ItemLeilao;

@Repository
public interface ItemLeilaoRepository extends CrudRepository<ItemLeilao, Integer> {

}
