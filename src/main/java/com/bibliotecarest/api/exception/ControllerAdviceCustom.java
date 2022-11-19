package com.bibliotecarest.api.exception;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.bibliotecarest.api.entity.Genero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceCustom {

	@ExceptionHandler(GeneroException.class)
	public ResponseEntity<Message<List<Genero>>> generoException(GeneroException ex) {
		List<Genero> listaGeneros = Arrays.asList(Genero.values()); // convert para list o vetor
		Message<List<Genero>> mensagem = new Message<>("Generos permitidos", listaGeneros);
		ResponseEntity<Message<List<Genero>>> response = new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
		return response;
	}

	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<Message<Long>> livroNaoEncontradoException(LivroNaoEncontradoException ex) {
		Message<Long> mensagem = new Message<>("Livro não encontrado", ex.getIdLivro());
		return new ResponseEntity<Message<Long>>(mensagem, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<Message<Long>> autorNaoEncontradoException(AutorNaoEncontradoException ex) {
		Message<Long> mensagem = new Message<>("Autor não encontrado", ex.getIdAutor());
		return new ResponseEntity<Message<Long>>(mensagem, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataNascimentoInvalidaException.class)
	public ResponseEntity<Message<LocalDate>> dataNascimentoInvalidaException(DataNascimentoInvalidaException ex){
		Message<LocalDate> message = new Message<>("Data de nascimento inválida", ex.getDataNascimento());
		return new ResponseEntity<Message<LocalDate>>(message, HttpStatus.BAD_REQUEST);
	}
}
