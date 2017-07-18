package br.net.mirante.exercicio1.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//	 * Uma cota��o � dada pelo n�mero do item, o valor e as credencias de um cliente. 
@Entity
@Table(name = "cotacao")
@SuppressWarnings("serial")
public class Cotacao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente = new Cliente();
	private LocalDateTime horaLance;

	@ManyToOne
	@JoinColumn(name = "itemLeilao_id")
	private ItemLeilao itemLeilao = new ItemLeilao();

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDateTime getHoraLance() {
		return horaLance;
	}

	public void setHoraLance(LocalDateTime horaLance) {
		this.horaLance = horaLance;
	}

	public ItemLeilao getItemLeilao() {
		return itemLeilao;
	}

	public void setItemLeilao(ItemLeilao itemLeilao) {
		this.itemLeilao = itemLeilao;
	}
	

}
