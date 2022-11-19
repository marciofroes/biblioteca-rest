package com.bibliotecarest.api.servico.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.bibliotecarest.api.dto.AutorDTO;
import com.bibliotecarest.api.entity.Autor;
import com.bibliotecarest.api.servico.AutorServico;
import com.bibliotecarest.api.servico.LivroServico;
import com.bibliotecarest.api.utils.Utilitarios;
import com.bibliotecarest.api.exception.DataNascimentoInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.bibliotecarest.api.exception.AutorNaoEncontradoException;
import com.bibliotecarest.api.repository.AutorRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AutorServicoImpl implements AutorServico {

	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private LivroServico livroServico;

	@Autowired
	private ConversionService conversionService;

	@Override
	public Autor cadastrarAutor(AutorDTO autorDTO) {
		if (!Utilitarios.validarDataNascimento(autorDTO.getDataNascimento()))
			throw new DataNascimentoInvalidaException();

		Autor autor = conversionService.convert(autorDTO, Autor.class);
		return autorRepository.save(autor);
	}

	@Override
	public List<Autor> listarAutor() {
		return autorRepository.findAll();
	}

	@Override
	public Autor atualizarAutor(AutorDTO novoAutor, Long idAutor) {
		Autor autor = buscarAutor(idAutor);
		if (ObjectUtils.isEmpty(autor)) {
			return null;
		}
		autor.setNome(novoAutor.getNome());
		autor.setDataNascimento(novoAutor.getDataNascimento());
		autor.setLivro(livroServico.listarLivros(novoAutor.getLivros()));
		return autorRepository.save(autor);
	}

	@Override
	public Autor buscarAutor(Long idAutor) {
		Optional<Autor> autor = autorRepository.findById(idAutor);
		if (ObjectUtils.isEmpty(autor)) {
			return null;
		}
		return autor.get();
	}

	@Override
	public boolean deletarAutor(Long idAutor) {
		Autor autor = buscarAutor(idAutor);
		if (Objects.isNull(autor)) {
			return false;
		}
		autorRepository.delete(autor);
		return true;
	}

	@Override
	public List<Autor> listarAutor(List<Long> listaIdAutor) {
		List<Autor> listaAutores = new ArrayList<>();

		for (Long id : listaIdAutor) {
			Optional.ofNullable(buscarAutor(id)).map(listaAutores::add)
					.orElseThrow(()-> new AutorNaoEncontradoException(id));
		}
		return listaAutores;
	}
}
