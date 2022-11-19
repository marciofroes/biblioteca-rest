package com.bibliotecarest.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@EqualsAndHashCode(exclude = "livro")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	private Long idAutor;
	private String nome;
	private LocalDate dataNascimento;
	@ManyToMany(mappedBy = "autor", cascade = { CascadeType.ALL}, fetch = FetchType.LAZY)
	@JsonBackReference
	private List<Livro> livro;

}
