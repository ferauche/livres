package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.dto.CadastroConsumidorDTO;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.repository.ConsumidorRepository;
import br.com.livresbs.livres.repository.PreComunidadeRepository;
import br.com.livresbs.livres.service.ConsumidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ConsumidorImpl implements ConsumidorService {
    @Autowired
    ConsumidorRepository cons;

    @Autowired
    PreComunidadeRepository pre;

    public List<Consumidor> listarConsumidor() {
        return cons.findAll();
    }

    public Consumidor listaConsumidorUnico(@PathVariable(value = "id") long id) {
        return cons.findById(id);
    }

    public Consumidor cadastraConsumidor(@RequestBody CadastroConsumidorDTO con) {

        pre.findById(con.getId_precomunidade());


        return cons.save(con);
    }

}
