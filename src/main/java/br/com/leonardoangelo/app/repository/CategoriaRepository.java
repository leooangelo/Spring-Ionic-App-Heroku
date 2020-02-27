package br.com.leonardoangelo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.leonardoangelo.app.domain.Categoria;
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{


}
