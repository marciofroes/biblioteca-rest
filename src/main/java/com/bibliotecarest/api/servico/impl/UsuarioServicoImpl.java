package com.bibliotecarest.api.servico.impl;

import java.util.List;
import java.util.Optional;

import com.bibliotecarest.api.dto.UsuarioDTO;
import com.bibliotecarest.api.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.bibliotecarest.api.repository.UsuarioRepositorio;
import com.bibliotecarest.api.servico.UsuarioServico;

@Service
public class UsuarioServicoImpl implements UsuarioServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private ConversionService conversionService;

	@Override
	public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO) {
		Usuario user = conversionService.convert(usuarioDTO, Usuario.class);
		return usuarioRepositorio.save(user);
	}

	@Override
	public List<Usuario> listarUsuario() {
		return usuarioRepositorio.findAll();
	}

	@Override
	public Usuario getUsuario(Long idUsuario) {
		Optional<Usuario> user = usuarioRepositorio.findById(idUsuario);
		if (ObjectUtils.isEmpty(user)) {
			return null;
		}
		return user.get();
	}

	@Override
	public Usuario atualizarUsuario(UsuarioDTO usuarioDTO, Long idUsuario) {
		Usuario user = this.getUsuario(idUsuario);
		if (ObjectUtils.isEmpty(user)) {
			return null;
		}
		user.setNome(usuarioDTO.getNome());
		user.setSenha(usuarioDTO.getSenha());
		user.setEmail(usuarioDTO.getEmail());
		user.setUser(usuarioDTO.getUser());
		user.setPerfil(usuarioDTO.getPerfil());

		return usuarioRepositorio.save(user);
	}

	@Override
	public boolean deletarUsuario(Long idUsuario) {
		Usuario user = this.getUsuario(idUsuario);
		if (ObjectUtils.isEmpty(user)) {
			return false;
		}
		usuarioRepositorio.deleteById(idUsuario);
		return true;
	}

	@Override
	public Usuario logar(String user, String senha) {
		if (StringUtils.isEmpty(senha) || StringUtils.isEmpty(user)) {
			return null;
		}
		return usuarioRepositorio.findByUserAndSenha(user, senha);
	}

}
