package com.bibliotecarest.api.converts;

import com.bibliotecarest.api.dto.LivroDTO;
import com.bibliotecarest.api.entity.Livro;
import com.bibliotecarest.api.servico.AutorServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bibliotecarest.api.entity.Livro.LivroBuilder;

@Component
public class DtoToLivroConverter implements Converter<LivroDTO, Livro> {

	@Autowired
	private AutorServico autorServico;

	@Override
	public Livro convert(LivroDTO livroDTO) {
		LivroBuilder livroBuilder = Livro.builder().autor(autorServico.listarAutor(livroDTO.getAutor()))
				.dataLancamento(livroDTO.getDataLancamento()).descricao(livroDTO.getDescricao())
				.genero(livroDTO.getGenero()).notaMedia(livroDTO.getNotaMedia()).titulo(livroDTO.getTitulo());

		return livroBuilder.build();
	}

}
