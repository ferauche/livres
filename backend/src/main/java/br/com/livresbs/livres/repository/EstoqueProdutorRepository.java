package br.com.livresbs.livres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livresbs.livres.model.EstoqueProdutor;

public interface EstoqueProdutorRepository extends JpaRepository<EstoqueProdutor, Integer>{

}
