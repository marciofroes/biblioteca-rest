package com.bibliotecarest.api.servico;

import java.util.List;

import com.bibliotecarest.api.dto.AutorDTO;
import com.bibliotecarest.api.entity.Autor;

public interface AutorServico {

	Autor cadastrarAutor(AutorDTO autor);

	List<Autor> listarAutor();

	Autor atualizarAutor(AutorDTO novoAutor, Long idAutor);

	Autor buscarAutor(Long idAutor);

	boolean deletarAutor(Long idAutor);

	List<Autor> listarAutor(List<Long> listaIdAutor);
}
