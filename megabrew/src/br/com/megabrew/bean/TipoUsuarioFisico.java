package br.com.megabrew.bean;

import javax.xml.bind.annotation.XmlEnumValue;

public enum TipoUsuarioFisico {
	@XmlEnumValue("bar")
	BAR,
	@XmlEnumValue("pub")
	PUB,
	@XmlEnumValue("restaurante")
	RESTAURANTE,
	@XmlEnumValue("lanchonete")
	LANCHONETE;
}
