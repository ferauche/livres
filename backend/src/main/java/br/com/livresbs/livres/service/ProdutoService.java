package br.com.livresbs.livres.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.Produto;

public interface ProdutoService {

	List<Produto> listaProdutos();
	Optional<Produto> listaProdutoId(Integer id);
	ResponseEntity<String> cadastrar(@RequestBody Produto produto);
	Produto editar(@RequestBody Produto produto);
	void excluir(@PathVariable Produto produto);

	ProdutosDisponiveisDTO listarProdutosDisponiveisCompraConsumidor(String cpf, Integer pagina);

}
