package com.bibliotecarest.api.repository;

import com.bibliotecarest.api.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	public Usuario findByUserAndSenha(String user, String senha);
}
