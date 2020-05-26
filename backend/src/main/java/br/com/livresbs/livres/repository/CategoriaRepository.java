package br.com.livresbs.livres.repository;

import java.util.List;
import br.com.livresbs.livres.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
}