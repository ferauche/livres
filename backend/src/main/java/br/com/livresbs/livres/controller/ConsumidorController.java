package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.ConsumidorDTO;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.service.impl.ConsumidorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ConsumidorController {
    @Autowired
    ConsumidorImpl cons;

    @CrossOrigin
    @GetMapping("/consumidor")
    public List<ConsumidorDTO> listarConsumidor() {
        return cons.listarConsumidor();
    }

    @CrossOrigin
    @GetMapping("/consumidor/{id}")
    public Consumidor listaConsumidorUnico(@PathVariable(value = "id") String id) {
        return cons.listaConsumidorUnico(id);
    }

    @CrossOrigin
    @PostMapping("/consumidor")
    public ResponseEntity cadastraConsumidor(@RequestBody ConsumidorDTO con) { return cons.cadastraConsumidor(con); }
}
