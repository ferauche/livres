package br.com.livresbs.livres.controller;

import java.util.List;

import br.com.livresbs.livres.service.PreComunidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.livresbs.livres.model.PreComunidade;
import br.com.livresbs.livres.service.impl.PreComunidadeImpl;

@RestController
@RequestMapping(value = "precomunidade")
public class PreComunidadeController {
    @Autowired
    private PreComunidadeService pcr;

    @CrossOrigin
    @GetMapping
    public List<PreComunidade> listaPreComunidades() {
        return pcr.listaPreComunidades();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public PreComunidade listaPreComunidadeUnica(@PathVariable(value = "id") long id) {
        return pcr.listaPreComunidadeUnica(id);
    }

    @CrossOrigin
    @PostMapping
    public PreComunidade cadastraPreComunidade(@RequestBody PreComunidade pc) {
        return pcr.cadastraPreComunidade(pc);
    }
    
    @CrossOrigin
    @PutMapping
    public PreComunidade editaPreComunidade(@RequestBody PreComunidade pc) {
    	return pcr.editaPreComunidade(pc);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluiPreComunidade(@PathVariable(value = "id") long id) { return pcr.excluiPreComunidade(id); }
}
