package br.com.livresbs.livres.service;

import java.util.List;
import java.util.Optional;

import br.com.livresbs.livres.dto.ProdutoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.Produto;

public interface ProdutoService {

	List<ProdutoDTO> listaProdutos();
	Optional<Produto> listaProdutoId(Integer id);
	ResponseEntity<String> cadastrar(@RequestBody ProdutoDTO produto);
	Produto editar(@RequestBody Produto produto);
	void excluir(@PathVariable Produto produto);

	ProdutosDisponiveisDTO listarProdutosDisponiveisCompraConsumidor(String cpf, Integer pagina, List<String> categorias);

	ResponseEntity<String> deletarProduto(Integer id);
}
