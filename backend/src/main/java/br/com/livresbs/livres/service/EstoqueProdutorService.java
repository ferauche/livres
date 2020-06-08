package br.com.livresbs.livres.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.livresbs.livres.model.EstoqueProdutor;

public interface EstoqueProdutorService {
	
	List<EstoqueProdutor> listarTodosEstoqueProdutor();
	EstoqueProdutor listaUnicoEstoqueProdutor(Integer id);
	ResponseEntity<String> cadastraEstoqueProdutor(@RequestBody EstoqueProdutor ep);
	ResponseEntity<String> atualizaEstoqueProdutor(@RequestBody EstoqueProdutor ep);
	ResponseEntity<String> deletaEstoqueProdutor(Integer id);
	
}
