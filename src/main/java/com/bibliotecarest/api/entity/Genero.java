package com.bibliotecarest.api.entity;

import org.springframework.util.StringUtils;

public enum Genero {

	ADMINISTRACAO("Administração"), AUTOAJUDA("Autoajuda"), BIOGRAFIA("Biografia"), CONTOS("Contos"), DICIONARIO(
			"Dicionario"), DIVERSOS("Diversos"), FICCAO("Ficção"), INFANTO_JUVENIL(
					"Infato Juvenil"), LINGUISTICA("Linguistiva"), POESIA(
							"Poesia"), ROMANCE("Romance"), TEORIA("Teoria"), TERROR("Terror"), TURISMO("Turismo");

	private String genero;

	private Genero(String valor) {
		this.genero = valor;
	}

	public String getValor() {
		return genero;
	}

	public boolean equals(String valor) {
		return this.genero.equals(valor);
	}

	public static Genero parse(String val) {
		if (StringUtils.isEmpty(val)) {
			return null;
		}

		for (Genero genero : Genero.values()) {
			if (genero.toString().equals(val.toUpperCase())) {
				return genero;
			}
		}
		return null;
	}
}
