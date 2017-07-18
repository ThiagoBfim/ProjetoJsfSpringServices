package br.net.mirante.exercicio1.soap;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "codigoItemLeilao",
    "valor",
    "login",
    "senha"
})
@XmlRootElement(name = "leilaoRequest")
public class LeilaoRequest {

	@XmlElement(required = true)
	private Integer codigoItemLeilao;
	
	@XmlElement(required = true)
	private BigDecimal valor;
	
	@XmlElement(name = "login", required = true)
	private String login;

	@XmlElement(name = "senha", required = true)
	private String senha;

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

}
