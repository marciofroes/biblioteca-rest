package com.bibliotecarest.api.converts;

import com.bibliotecarest.api.dto.UsuarioDTO;
import com.bibliotecarest.api.entity.Usuario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bibliotecarest.api.entity.Usuario.UsuarioBuilder;

@Component
public class DtoToUsuarioConverter implements Converter<UsuarioDTO, Usuario> {

	@Override
	public Usuario convert(UsuarioDTO usuarioDTO) {
		UsuarioBuilder usuarioBuilder = Usuario.builder().email(usuarioDTO.getEmail()).nome(usuarioDTO.getNome())
				.perfil(usuarioDTO.getPerfil()).senha(usuarioDTO.getSenha()).user(usuarioDTO.getUser());

		return usuarioBuilder.build();
	}

}
