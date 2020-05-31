package br.com.livresbs.livres.repository;

import br.com.livresbs.livres.model.DataEntrega;
import br.com.livresbs.livres.model.EstoqueProdutor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstoqueProdutorRepository extends JpaRepository<EstoqueProdutor, Integer>{

    Page<EstoqueProdutor> findByDatasEntrega(DataEntrega dataEntrega, Pageable pagina);

    Page<EstoqueProdutor> findByDatasEntregaAndProdutoCategoriaNomeIn(DataEntrega dataEntrega, List<String> categorias, Pageable pagina);

}
