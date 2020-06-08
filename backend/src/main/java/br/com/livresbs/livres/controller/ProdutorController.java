package br.com.livresbs.livres.controller;

import java.util.List;

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

import br.com.livresbs.livres.model.Produtor;
import br.com.livresbs.livres.service.ProdutorService;

@RestController
@RequestMapping(value="/api")
public class ProdutorController {

	@Autowired
	ProdutorService ps;
	
	@CrossOrigin
	@GetMapping("/listarProdutores")
	public List<Produtor> listarTodosProdutos(){
		return ps.listarProdutores();
	}
	
	@CrossOrigin
	@GetMapping("/listarUnicoProdutor/{id}")
	public Produtor listarUnicoProdutor(@PathVariable(value = "id") String id) {
		return ps.listarUnicoProdutor(id);
	}
	
	@CrossOrigin
	@PostMapping("/cadastrarProdutor")
	public ResponseEntity<String> cadastarProdutor(@RequestBody Produtor produtor){
		return ps.cadastraProdutor(produtor);
	}
	
	@CrossOrigin
	@PutMapping("/atualizarProdutor")
	public ResponseEntity<String> atualizarProdutores(@RequestBody Produtor produtor){
		return ps.atualizaProduto(produtor);
	}
	
	@CrossOrigin
	@DeleteMapping("/deletarProdutor/{id}")
	public ResponseEntity<String> deletaProdutor(@PathVariable(value="id") String id){
		return ps.deletarProduto(id);
	}
	
	
}
