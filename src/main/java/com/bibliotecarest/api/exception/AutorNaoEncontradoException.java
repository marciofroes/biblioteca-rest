package com.bibliotecarest.api.exception;

public class AutorNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Long idAutor;

	public AutorNaoEncontradoException(Long idAutor) {
		super();
		this.idAutor = idAutor;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}

}
