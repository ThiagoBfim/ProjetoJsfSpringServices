package br.net.mirante.exercicio1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.net.mirante.exercicio1.modelo.Cotacao;

@Repository
public interface CotacaoRepository extends CrudRepository<Cotacao, Integer> {

	@Query("Select cotacao from Cotacao cotacao where cotacao.itemLeilao.codigo =?1 "
			+ " AND cotacao.valor = (Select max(cot.valor) from Cotacao cot where cotacao.itemLeilao.codigo =?1)")
	Cotacao carregarByLeilao(Integer codigoLeilao);

}
