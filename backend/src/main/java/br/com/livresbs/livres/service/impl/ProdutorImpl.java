package br.com.livresbs.livres.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.livresbs.livres.model.Produtor;
import br.com.livresbs.livres.repository.ProdutorRepository;
import br.com.livresbs.livres.service.ProdutorService;

@Service
public class ProdutorImpl implements ProdutorService {
	@Autowired
	ProdutorRepository pr;
	
	@Override
	public List<Produtor> listarProdutores() {
		return pr.findAll();
	}

	@Override
	public Produtor listarUnicoProdutor(String id) {
		if(pr.existsById(id)) {
			return pr.findById(id).get();
		}
		
		return null;
	}

	@Override
	public ResponseEntity<String> cadastraProdutor(Produtor produtor) {
		
		if(pr.existsById(produtor.getProdutor())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Produtor de CNPJ "+ produtor.getProdutor() + " já está cadastrado!");
		}
		pr.save(produtor);
		return ResponseEntity.ok("Produtor cadastrado com sucesso!");
	}

	@Override
	public ResponseEntity<String> atualizaProduto(Produtor produtor) {
		if(pr.existsById(produtor.getProdutor())) {
			pr.save(produtor);
			//TODO testar para ver se funciona
			return ResponseEntity.status(HttpStatus.OK).body("Produtor cadastrado com sucesso!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não Foi possivel atualizar Produtor!");
	}

	@Override
	public ResponseEntity<String> deletarProduto(String id) {
		if(pr.existsById(id)) {
			pr.deleteById(id);
			return ResponseEntity.ok("Produtor deletado com sucesso!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível deletar produtor");
	}

}
