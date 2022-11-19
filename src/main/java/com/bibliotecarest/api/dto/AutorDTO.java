package com.bibliotecarest.api.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AutorDTO {

	@NotEmpty
	private String nome;
	@NotNull
	private LocalDate dataNascimento;
	private List<Long> livros;

}
