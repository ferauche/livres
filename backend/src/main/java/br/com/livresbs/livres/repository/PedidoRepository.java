package br.com.livresbs.livres.repository;


import br.com.livresbs.livres.model.Pedido;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
