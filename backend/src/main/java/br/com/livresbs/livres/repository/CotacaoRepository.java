package br.com.livresbs.livres.repository;

import br.com.livresbs.livres.model.Cotacao;
import br.com.livresbs.livres.model.DataEntrega;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {

    Page<Cotacao> findByDataEntregaAndProdutoCategoriaIn(
            DataEntrega dataEntrega,
            List<String> categorias,
            Pageable pageable
    );

    Page<Cotacao> findByDataEntrega(DataEntrega dataEntrega, Pageable pageable);

}