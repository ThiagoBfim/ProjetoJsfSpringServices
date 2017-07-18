package br.net.mirante.exercicio1.soap;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cotacao", propOrder = {
    "codigoItemLeilao",
    "valor",
    "login",
    "senha"
})
public class CotacaoWbs {

	private Integer codigoItemLeilao;
	private BigDecimal valor;
	
	private String login;

	private String senha;

	public Integer getCodigoItemLeilao() {
		return codigoItemLeilao;
	}

	public void setCodigoItemLeilao(Integer codigoItemLeilao) {
		this.codigoItemLeilao = codigoItemLeilao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}



}
