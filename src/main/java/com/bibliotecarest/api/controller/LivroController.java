package com.bibliotecarest.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.bibliotecarest.api.dto.LivroDTO;
import com.bibliotecarest.api.entity.Livro;
import com.bibliotecarest.api.servico.LivroServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/biblioteca/livros")
public class LivroController {

	@Autowired
	private LivroServico livroServico;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Livro> cadastrarLivro(@Valid @RequestBody LivroDTO livroDTO) {
		return new ResponseEntity<Livro>(livroServico.cadastrarLivro(livroDTO), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listarLivros(@RequestParam(name = "titulo", required = false) String titulo,
			@RequestParam(name = "genero", required = false) String genero,
			@RequestParam(name = "autor", required = false) String autor) {
		List<Livro> livros = livroServico.listarLivros(titulo, genero, autor);

		if (CollectionUtils.isEmpty(livros)) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<List<Livro>>(livros, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarLivro(@PathVariable(name = "id") Long id) {
		if (livroServico.deletarLivro(id)) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Livro> buscarLivro(@PathVariable(value = "id") Long id) {
		Optional<Livro> livro = livroServico.buscarLivro(id);
		if (ObjectUtils.isEmpty(livro)) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Livro>(livro.get(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Livro> atualizarLivro(@Valid @RequestBody LivroDTO livroDTO,
			@PathVariable(name = "id") Long id) {
		Livro livro = livroServico.atualizarLivro(livroDTO, id);
		if (ObjectUtils.isEmpty(livro)) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Livro>(livro, HttpStatus.OK);
	}
}
