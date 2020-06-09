package br.com.livresbs.livres.service.impl;

import java.util.ArrayList;
import java.util.List;

import br.com.livresbs.livres.dto.EstoqueProdutorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.livresbs.livres.model.EstoqueProdutor;
import br.com.livresbs.livres.repository.EstoqueProdutorRepository;
import br.com.livresbs.livres.repository.ProdutoRepository;
import br.com.livresbs.livres.repository.ProdutorRepository;
import br.com.livresbs.livres.service.EstoqueProdutorService;
import lombok.var;

@Service
public class EstoqueProdutorImpl implements EstoqueProdutorService{

	@Autowired
	EstoqueProdutorRepository epr;
	
	@Autowired
	ProdutoRepository pr;
	
	@Autowired
	ProdutorRepository prr;
	
	
	@Override
	public List<EstoqueProdutorDTO> listarTodosEstoqueProdutor() {
		
		List<EstoqueProdutorDTO> lista = new ArrayList();
		
		
		epr.findAll().forEach(x ->{
			EstoqueProdutorDTO ep = EstoqueProdutorDTO.builder()
									.id(x.getId())
									.preco(x.getPreco())
									.produto_id(x.getProduto().getId())
									.produtor_id(x.getProdutor().getCnpj())
									.quantidade(x.getQuantidade())
									.unidadeMedida_id(x.getUnidadeMedida().getId())
									.dataEntregaList(x.getDatasEntrega())
									.build();
			lista.add(ep);
		});
		
	
		return lista;
	}

	@Override
	public EstoqueProdutor listaUnicoEstoqueProdutor(Integer id) {
		
		if(epr.existsById(id)) {
			return epr.findById(id).get();
		}
		return null;
	}

	@Override
	public ResponseEntity<String> cadastraEstoqueProdutor(EstoqueProdutor ep) {
		
		if(epr.save(ep) != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Estoque de Produtor cadastrado com sucesso!");
		}
			
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel cadastrar estoque de produtor");
	}

	@Override
	public ResponseEntity<String> atualizaEstoqueProdutor(EstoqueProdutor ep) {
		if(epr.existsById(ep.getId())) {
			epr.save(ep);
			return ResponseEntity.status(HttpStatus.OK).body("Estoque de Produtor atualizado com sucesso!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel cadastrar estoque de produtor");
	}

	@Override
	public ResponseEntity<String> deletaEstoqueProdutor(Integer id) {
		if(epr.existsById(id)) {
			epr.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Estoque de Produtor deletado com sucesso!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel deletar estoque de produtor");
	
	}

}
