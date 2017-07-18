package br.net.mirante.exercicio1.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "itemLeilao")
@SuppressWarnings("serial")
public class ItemLeilao implements Serializable, SampleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	private String descricao;
	private String nome;
	private LocalDateTime dataFimLance;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataFimLance() {
		return dataFimLance;
	}

	public void setDataFimLance(LocalDateTime dataFimLance) {
		this.dataFimLance = dataFimLance;
	}

}
