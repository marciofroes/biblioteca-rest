package com.bibliotecarest.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.bibliotecarest.api.dto.AutorDTO;
import com.bibliotecarest.api.entity.Autor;
import com.bibliotecarest.api.servico.AutorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/biblioteca/autores")
public class AutorController {

	@Autowired
	private AutorServico autorServico;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Autor> cadastrarAutor(@Valid @RequestBody AutorDTO autor) {
		return new ResponseEntity<Autor>(autorServico.cadastrarAutor(autor), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Autor>> listarAutor() {
		List<Autor> listaAutores = autorServico.listarAutor();
		if (CollectionUtils.isEmpty(listaAutores)) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<List<Autor>>(listaAutores, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Autor> buscarAutor(@PathVariable(value = "id") Long id) {
		Autor autor = autorServico.buscarAutor(id);
		if (ObjectUtils.isEmpty(autor)) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarAutor(@PathVariable(name = "id") Long id) {
		if (autorServico.deletarAutor(id)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Autor> atualizarAutor(@Valid @RequestBody AutorDTO autorNovo,
			@PathVariable(value = "id") Long id) {
		Autor autor = autorServico.atualizarAutor(autorNovo, id);
		if (ObjectUtils.isEmpty(autor)) {
			return new ResponseEntity<Autor>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Autor>(autor, HttpStatus.OK);
	}
}
