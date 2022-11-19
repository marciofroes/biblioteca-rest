package com.bibliotecarest.api.servico.impl;

import com.bibliotecarest.api.dto.LivroDTO;
import com.bibliotecarest.api.entity.Genero;
import com.bibliotecarest.api.entity.Livro;
import com.bibliotecarest.api.servico.AutorServico;
import com.bibliotecarest.api.servico.LivroServico;
import com.bibliotecarest.api.exception.GeneroException;
import com.bibliotecarest.api.exception.LivroNaoEncontradoException;
import com.bibliotecarest.api.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroServicoImpl implements LivroServico {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorServico autorServico;

    @Autowired
    private ConversionService conversionService;

    @Override
    @Transactional
    public Livro cadastrarLivro(LivroDTO livroDTO) {
        Livro livro = conversionService.convert(livroDTO, Livro.class);
        livro.setDataCadastro(LocalDate.now());
        return livroRepository.save(livro);
    }

    @Override
    public List<Livro> listarLivros(String titulo, String genero, String autor) {
        Sort sort = Sort.by(Direction.DESC, "dataLancamento");
        if (!StringUtils.isEmpty(titulo)) {
            return livroRepository.findByTituloContaining(titulo, sort);
        } else if (!StringUtils.isEmpty(genero)) {
            Genero generoParsed = Genero.parse(genero);
            if (Objects.isNull(generoParsed)) {
                throw new GeneroException();
            }
            return livroRepository.findByGenero(generoParsed, sort);
        } else if (!StringUtils.isEmpty(autor)) {
            livroRepository.findByAutor(autor, sort);
        }
        return livroRepository.findAll(sort);
    }

    @Override
    public Optional<Livro> buscarLivro(Long idLivro) {
        Optional<Livro> livro = livroRepository.findById(idLivro);
        if (ObjectUtils.isEmpty(livro)) {
            return null;
        }
        return livro;
    }

    @Override
    public boolean deletarLivro(Long idLivro) {
        Optional<Livro> livro = livroRepository.findById(idLivro);
        if (ObjectUtils.isEmpty(livro)) {
            return false;
        }
        livroRepository.deleteById(idLivro);
        return true;
    }

    @Override
    public Livro atualizarLivro(LivroDTO novoLivro, Long id) {
        if (ObjectUtils.isEmpty(this.buscarLivro(id))) {
            return null;
        }
        Livro livro = Livro.builder().idLivro(id).autor(autorServico.listarAutor(novoLivro.getAutor()))
                .dataLancamento(novoLivro.getDataLancamento()).descricao(novoLivro.getDescricao())
                .genero(novoLivro.getGenero()).notaMedia(novoLivro.getNotaMedia())
                .titulo(novoLivro.getTitulo()).build();
        return livroRepository.save(livro);
    }

    @Override
    public List<Livro> listarLivros(List<Long> listaIdLivros) {

        return listaIdLivros.stream().map(id -> buscarLivro(id)
                .orElseThrow(() -> new LivroNaoEncontradoException(id)))
                .collect(Collectors.toList());
    }

}
