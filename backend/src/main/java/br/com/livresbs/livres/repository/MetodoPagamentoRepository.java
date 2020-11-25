package br.com.livresbs.livres.repository;

import br.com.livresbs.livres.model.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento, Integer> {

    Optional<MetodoPagamento> findByNome(String nome);

}