package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.dto.ConsumidorDTO;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.model.PreComunidade;
import br.com.livresbs.livres.repository.ConsumidorRepository;
import br.com.livresbs.livres.repository.PreComunidadeRepository;
import br.com.livresbs.livres.service.ConsumidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsumidorImpl implements ConsumidorService {
    @Autowired
    ConsumidorRepository cons;

    @Autowired
    PreComunidadeRepository pre;

    public List<ConsumidorDTO> listarConsumidor() {
        List<ConsumidorDTO> listConsdto = new ArrayList<>();
        cons.findAll().forEach(consumidor -> {
        	
      
        	
            ConsumidorDTO builderDto = ConsumidorDTO.builder()
                    .nome(consumidor.getNome())
                    .cpf(consumidor.getCpf())
                    .sobrenome(consumidor.getSobrenome())
                    .precomunidade(consumidor.getPrecomunidade().getId())
                    .build();

            listConsdto.add(builderDto);
        });
        return listConsdto;
    }

    public Consumidor listaConsumidorUnico(@PathVariable(value = "id") long id) {
        return cons.findById(id);
    }

    public void cadastraConsumidor(@RequestBody ConsumidorDTO con) {

        Optional<PreComunidade> oppre = pre.findById(con.getPrecomunidade());
        if(!oppre.isPresent()){
            //TODO tratamente caso precomunidade nao exista
        }

        Consumidor consumidor = Consumidor.builder()
                .cpf(con.getCpf())
                .nome(con.getNome())
                .sobrenome(con.getSobrenome())
                .senha(con.getSenha())
                .precomunidade(oppre.get())
                .build();

        cons.save(consumidor);
    }

}