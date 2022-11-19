package com.bibliotecarest.api.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bibliotecarest.api.entity.Genero;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LivroDTO {

	@NotEmpty
	private String titulo;
	private List<Long> autor;
	@NotNull
	private LocalDate dataLancamento;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Genero genero;
	@NotEmpty
	private String descricao;
	@NotNull
	private int notaMedia;
}
