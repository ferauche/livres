package br.com.livresbs.livres.repository;

import br.com.livresbs.livres.model.DataEntrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataEntregaRepository extends JpaRepository<DataEntrega, Long> {

    @Query(
            value = "select de.* from tb_data_entrega de " +
                    "inner join tb_precomunidade pre " +
                    "on pre.id = de.precomunidade_id " +
                    "inner join tb_consumidor c " +
                    "on c.precomunidade_id = pre.id " +
                    "where c.cpf = :cpf " +
                    "and de.prazo_limite_pedido >= CURDATE() " +
                    "order by de.prazo_limite_pedido asc limit 1",
            nativeQuery = true
    )
    DataEntrega encontrarDataEntregaAtivaConsumidor(@Param("cpf") String cpf);

}
