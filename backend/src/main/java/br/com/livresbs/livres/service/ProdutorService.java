package br.com.livresbs.livres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.livresbs.livres.model.Produtor;

public interface ProdutorService {
	
		List<Produtor> listarProdutores();
		Produtor listarUnicoProdutor(String id);
	    ResponseEntity<String> cadastraProdutor(@RequestBody Produtor produtor);
	    ResponseEntity<String> atualizaProduto(@RequestBody Produtor produtor);
	    ResponseEntity<String> deletarProduto(String id);
}
