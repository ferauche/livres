package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.model.PreComunidade;
import br.com.livresbs.livres.repository.PreComunidadeRepository;
import br.com.livresbs.livres.service.PreComunidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return pcr.findById(id).get();
    }

    public PreComunidade cadastraPreComunidade(@RequestBody PreComunidade pc) {
        return pcr.save(pc);
    }


	public PreComunidade editaPreComunidade(PreComunidade pc) {
		return pcr.save(pc);
	}

    @Override
    public ResponseEntity<String> excluiPreComunidade(long id) {

        if(!pcr.existsById(id))
            return ResponseEntity.status(HttpStatus.OK).body("Pre Comunidade Não Encontrada!");

        pcr.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Excluído com Sucesso!");

    }


}
