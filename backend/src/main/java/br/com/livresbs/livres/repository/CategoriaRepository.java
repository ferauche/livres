package br.com.livresbs.livres.repository;

import br.com.livresbs.livres.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaProduto, Integer> {

    Optional<CategoriaProduto> findByCategoria(String categoria);
    
}