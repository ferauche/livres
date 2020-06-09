package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.ConsumidorDTO;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.service.ConsumidorService;
import br.com.livresbs.livres.service.impl.ConsumidorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "consumidor")
public class ConsumidorController {
    @Autowired
    private ConsumidorService cons;

    @CrossOrigin
    @GetMapping
    public List<ConsumidorDTO> listarConsumidor() {
        return cons.listarConsumidor();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Consumidor listaConsumidorUnico(@PathVariable(value = "id") String id) {
        return cons.listaConsumidorUnico(id);
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<String> editaConsumidor(@RequestBody ConsumidorDTO consumidor){
        return cons.editaConsumidor(consumidor);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity cadastraConsumidor(@RequestBody ConsumidorDTO con) { return cons.cadastraConsumidor(con); }
    
    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deletaConsumidor(@PathVariable(value = "id") String id) {
    	cons.deletarConsumidor(id);
    }
}
