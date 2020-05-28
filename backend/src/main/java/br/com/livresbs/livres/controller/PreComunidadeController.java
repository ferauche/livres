package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.model.PreComunidade;
import br.com.livresbs.livres.service.impl.PreComunidadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
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
}
