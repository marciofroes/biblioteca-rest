package com.bibliotecarest.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.bibliotecarest.api.dto.LoginDTO;
import com.bibliotecarest.api.dto.UsuarioDTO;
import com.bibliotecarest.api.entity.Usuario;
import com.bibliotecarest.api.servico.UsuarioServico;
import com.bibliotecarest.api.session.Session;
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
@RequestMapping("/api/v1/biblioteca/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioServico usuarioServico;

	@Autowired
	private Session sessao;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		return new ResponseEntity<Usuario>(usuarioServico.cadastrarUsuario(usuarioDTO), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listarUsuario() {
		List<Usuario> listaUser = usuarioServico.listarUsuario();
		if (CollectionUtils.isEmpty(listaUser)) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<List<Usuario>>(listaUser, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "id") Long idUsuario) {
		Usuario user = usuarioServico.getUsuario(idUsuario);
		if (ObjectUtils.isEmpty(user)) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarUsuario(@PathVariable(value = "id") Long idUsuario) {
		if (usuarioServico.deletarUsuario(idUsuario)) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuario> atualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO,
			@PathVariable(value = "id") Long idUsuario) {
		Usuario user = usuarioServico.atualizarUsuario(usuarioDTO, idUsuario);
		if (ObjectUtils.isEmpty(user)) {
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/logar", method = RequestMethod.POST)
	public ResponseEntity<Usuario> logar(@Valid @RequestBody LoginDTO loginDTO) {
		Usuario user = usuarioServico.logar(loginDTO.getUser(), loginDTO.getSenha());
		if (ObjectUtils.isEmpty(user)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		sessao.setUsuario(user);
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public ResponseEntity<Void> logout() {
		if (ObjectUtils.isEmpty(sessao.getUsuario())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		sessao.setUsuario(null);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
