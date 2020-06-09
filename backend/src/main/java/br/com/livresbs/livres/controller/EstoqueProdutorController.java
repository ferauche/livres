package br.com.livresbs.livres.controller;

import java.util.List;

import br.com.livresbs.livres.dto.EstoqueProdutorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.livresbs.livres.model.EstoqueProdutor;
import br.com.livresbs.livres.service.EstoqueProdutorService;

@RestController
@RequestMapping(value = "estoqueprodutor")
public class EstoqueProdutorController {
	
	@Autowired
	private EstoqueProdutorService eps;
	
	@CrossOrigin
	@GetMapping
	public List<EstoqueProdutorDTO>listarEstoqueProdutor(){
		return eps.listarTodosEstoqueProdutor();
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	EstoqueProdutor listarUnicoEstoqueProdutor(@PathVariable(value="id") Integer id) {
		return eps.listaUnicoEstoqueProdutor(id);
	}
	
	@CrossOrigin
	@PostMapping
	ResponseEntity<String> cadastrarEstoqueProdutor(@RequestBody EstoqueProdutor ep){
		return eps.cadastraEstoqueProdutor(ep);
	}
	
	@CrossOrigin
	@PutMapping
	ResponseEntity<String> atualizarEstoqueProdutor(@RequestBody EstoqueProdutor ep){
		return eps.atualizaEstoqueProdutor(ep);
	}
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	ResponseEntity<String> deletarEstoqueProdutor(@PathVariable(value="id") Integer id) {
		return eps.deletaEstoqueProdutor(id);
	}
	
	
	
}
