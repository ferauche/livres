package br.com.livresbs.livres.repository;

import br.com.livresbs.livres.model.Consumidor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumidorRepository extends JpaRepository<Consumidor, String> {
}
