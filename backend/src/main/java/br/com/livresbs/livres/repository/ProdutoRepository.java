package br.com.livresbs.livres.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.livresbs.livres.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
