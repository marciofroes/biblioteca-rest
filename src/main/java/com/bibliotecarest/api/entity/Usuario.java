package com.bibliotecarest.api.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUsuario;
	private String nome;
	private String user;
	private String senha;
	private String email;
	@Enumerated(value = EnumType.STRING)
	private Perfil perfil;
	@OneToMany(mappedBy = "usuario")
	private Classificacao classificacao;

}
