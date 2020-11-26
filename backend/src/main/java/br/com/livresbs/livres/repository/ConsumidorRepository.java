package br.com.livresbs.livres.repository;


import br.com.livresbs.livres.model.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsumidorRepository extends JpaRepository<Consumidor, String> {
}
