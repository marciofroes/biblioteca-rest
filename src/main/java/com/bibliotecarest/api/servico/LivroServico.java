package com.bibliotecarest.api.servico;

import java.util.List;
import java.util.Optional;

import com.bibliotecarest.api.dto.LivroDTO;
import com.bibliotecarest.api.entity.Livro;

public interface LivroServico {

	public Livro cadastrarLivro(LivroDTO livro);

	public List<Livro> listarLivros(String titulo, String genero, String autor);

	public Optional<Livro> buscarLivro(Long idLivro);

	public boolean deletarLivro(Long idLivro);

	public Livro atualizarLivro(LivroDTO livro, Long id);

	public List<Livro> listarLivros(List<Long> listaIdLivros);
}
