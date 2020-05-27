package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.model.PreComunidade;
import br.com.livresbs.livres.repository.PreComunidadeRepository;
import br.com.livresbs.livres.service.PreComunidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PreComunidadeImpl implements PreComunidadeService {
    @Autowired
    PreComunidadeRepository pcr;

    public List<PreComunidade> listaPreComunidades() {
        return pcr.findAll();
    }

    public PreComunidade listaPreComunidadeUnica(@PathVariable(value = "id") long id) {
        return pcr.findById(id);
    }

    public PreComunidade cadastraPreComunidade(@RequestBody PreComunidade pc) {
        return pcr.save(pc);
    }

}
