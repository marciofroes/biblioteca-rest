package com.bibliotecarest.api.converts;

import java.util.ArrayList;

import com.bibliotecarest.api.dto.AutorDTO;
import com.bibliotecarest.api.entity.Autor;
import com.bibliotecarest.api.servico.LivroServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.bibliotecarest.api.entity.Autor.AutorBuilder;

@Component
public class DtoToAutorConverter implements Converter<AutorDTO, Autor> {

	@Autowired
	private LivroServico livroServico;

	@Override
	public Autor convert(AutorDTO autorDTO) {
		AutorBuilder autorBuilder = Autor.builder().nome(autorDTO.getNome())
				.dataNascimento(autorDTO.getDataNascimento());

		if (!ObjectUtils.isEmpty(autorDTO.getLivros())) {
			autorBuilder.livro(livroServico.listarLivros(autorDTO.getLivros()));
		} else {
			autorBuilder.livro(new ArrayList<>());
		}
		return autorBuilder.build();
	}

}
