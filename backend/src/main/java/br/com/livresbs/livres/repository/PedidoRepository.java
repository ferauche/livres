package br.com.livresbs.livres.repository;


import br.com.livresbs.livres.model.Pedido;
import br.com.livresbs.livres.model.StatusPedido;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByStatus(StatusPedido status);
}
