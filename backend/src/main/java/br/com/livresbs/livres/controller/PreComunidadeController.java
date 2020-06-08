package br.com.livresbs.livres.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livresbs.livres.model.PreComunidade;
import br.com.livresbs.livres.service.impl.PreComunidadeImpl;

@RestController
@RequestMapping
public class PreComunidadeController {
    @Autowired
    PreComunidadeImpl pcr;

    @CrossOrigin
    @GetMapping("/precomunidade")
    public List<PreComunidade> listaPreComunidades() {
        return pcr.listaPreComunidades();
    }

    @CrossOrigin
    @GetMapping("/precomunidade/{id}")
    public PreComunidade listaPreComunidadeUnica(@PathVariable(value = "id") long id) {
        return pcr.listaPreComunidadeUnica(id);
    }

    @CrossOrigin
    @PostMapping("/precomunidade")
    public PreComunidade cadastraPreComunidade(@RequestBody PreComunidade pc) {
        return pcr.cadastraPreComunidade(pc);
    }
    
    @CrossOrigin
    @PutMapping("/precomunidade")
    public PreComunidade editaPreComunidade(@RequestBody PreComunidade pc) {
    	return pcr.editaPreComunidade(pc);
    }
}
