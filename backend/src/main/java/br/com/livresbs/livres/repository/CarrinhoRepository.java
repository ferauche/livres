package br.com.livresbs.livres.repository;

import br.com.livresbs.livres.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {

    Optional<Carrinho> findByConsumidorCpfAndEstoqueProdutorId(String cpf, Integer id);

    List<Carrinho> findByConsumidorCpf(String cpf);

}
