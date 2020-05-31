package br.com.livresbs.livres.service;

import java.util.List;
import java.util.Optional;

import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.Produto;

public interface ProdutoService {

	List<Produto> listaProdutos();
	Optional<Produto> listaProdutoId(Integer id);
	Produto cadastrar(Produto produto);
	Produto editar(Produto produto);
	void excluir(Produto produto);

	ProdutosDisponiveisDTO listarProdutosDisponiveisCompraConsumidor(String cpf, Integer pagina);

}
