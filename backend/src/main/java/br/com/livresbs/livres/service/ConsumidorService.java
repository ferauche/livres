package br.com.livresbs.livres.service;

import br.com.livresbs.livres.model.Consumidor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ConsumidorService {
    List<Consumidor> listarConsumidor();

    Consumidor listaConsumidorUnico(@PathVariable(value = "id") long id);

    Consumidor cadastraConsumidor(@RequestBody Consumidor con);
}
