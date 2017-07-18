package br.net.mirante.exercicio1.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.net.mirante.exercicio1.modelo.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

	public Optional<Cliente> findByNomeAndSenha(String nome, String senha);

}
