package br.net.mirante.exercicio1.jms;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import br.net.mirante.exercicio1.modelo.Cliente;
import br.net.mirante.exercicio1.modelo.Cotacao;
import br.net.mirante.exercicio1.modelo.ItemLeilao;

public class CotacaoRequestJms {

	private Integer codigo;
	private BigDecimal valor;
	private Cliente cliente = new Cliente();
	private Date horaLance;
	private Integer codItemLeilao;

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

	public Date getHoraLance() {
		return horaLance;
	}

	public void setHoraLance(Date horaLance) {
		this.horaLance = horaLance;
	}

	public Integer getCodItemLeilao() {
		return codItemLeilao;
	}

	public void setCodItemLeilao(Integer codItemLeilao) {
		this.codItemLeilao = codItemLeilao;
	}

	public Cotacao toEntity() {
		LocalDateTime ldt = LocalDateTime.ofInstant(getHoraLance().toInstant(), ZoneId.systemDefault());
		Cotacao cotacao = new Cotacao();
		cotacao.setCliente(cliente);
		cotacao.setHoraLance(ldt);
		cotacao.setValor(valor);
		ItemLeilao itemLeilao = new ItemLeilao();
		itemLeilao.setCodigo(codItemLeilao);
		cotacao.setItemLeilao(itemLeilao);
		return cotacao;

	}

}
