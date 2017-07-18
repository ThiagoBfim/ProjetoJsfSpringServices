package br.net.mirante.exercicio1.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultado", propOrder = { "codigoLeilao" })
public class ResultadoWbs {

	@XmlElement(required = true)
	protected Integer codigoLeilao;

	public Integer getCodigoLeilao() {
		return codigoLeilao;
	}

	public void setCodigoLeilao(Integer codigoLeilao) {
		this.codigoLeilao = codigoLeilao;
	}



	

}
