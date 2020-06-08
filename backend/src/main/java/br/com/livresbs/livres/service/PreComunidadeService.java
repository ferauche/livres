package br.com.livresbs.livres.service;

import br.com.livresbs.livres.model.PreComunidade;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PreComunidadeService {

    List<PreComunidade> listaPreComunidades();

    PreComunidade listaPreComunidadeUnica(@PathVariable(value = "id") long id);

    PreComunidade cadastraPreComunidade(@RequestBody PreComunidade pc);
    
    PreComunidade editaPreComunidade(@RequestBody PreComunidade pc);
}
