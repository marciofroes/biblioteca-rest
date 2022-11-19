package com.bibliotecarest.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bibliotecarest.api.entity.Perfil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioDTO {

	@NotEmpty
	private String nome;
	@NotEmpty
	private String user;
	@NotBlank
	private String senha;
	@NotEmpty
	@Email
	private String email;
	@NotNull
	private Perfil perfil;
}
