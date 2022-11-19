package com.bibliotecarest.api.servico;

import java.util.List;

import com.bibliotecarest.api.dto.UsuarioDTO;
import com.bibliotecarest.api.entity.Usuario;

public interface UsuarioServico {

	public Usuario cadastrarUsuario(UsuarioDTO usuarioDTO);

	public List<Usuario> listarUsuario();

	public Usuario getUsuario(Long idUsuario);

	public Usuario atualizarUsuario(UsuarioDTO usuario, Long idUsuario);

	public boolean deletarUsuario(Long idUsuario);
	
	public Usuario logar(String user, String senha);
}
