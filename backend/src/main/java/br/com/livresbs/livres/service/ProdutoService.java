package br.com.livresbs.livres.service;

import java.util.List;

import br.com.livresbs.livres.dto.ProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.livresbs.livres.model.Produto;

public interface ProdutoService {

	List<ProdutoDTO> listaProdutos();
	ProdutoDTO listaProdutoId(Integer id);
	ResponseEntity<String> cadastrar(@RequestBody ProdutoDTO produto);
	Produto editar(@RequestBody Produto produto);
	void excluir(@PathVariable Produto produto);
	ResponseEntity<String> deletarProduto(Integer id);

}
