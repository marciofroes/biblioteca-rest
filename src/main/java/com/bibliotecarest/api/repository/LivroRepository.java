package com.bibliotecarest.api.repository;

import java.util.List;

import com.bibliotecarest.api.entity.Genero;
import com.bibliotecarest.api.entity.Livro;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
	
	public List<Livro> findByTituloContaining(String titulo, Sort sort);

	public List<Livro> findByGenero(Genero genero, Sort sort);
	
	public List<Livro> findByAutor(String autor, Sort sort);
}
