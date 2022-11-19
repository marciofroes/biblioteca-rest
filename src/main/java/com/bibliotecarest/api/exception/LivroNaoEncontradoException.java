package com.bibliotecarest.api.exception;

public class LivroNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long idLivro;

	public LivroNaoEncontradoException(Long idLivro) {
		super();
		this.idLivro = idLivro;
	}

	public Long getIdLivro() {
		return idLivro;
	}

	public void setIdLivro(Long idLivro) {
		this.idLivro = idLivro;
	}
}
