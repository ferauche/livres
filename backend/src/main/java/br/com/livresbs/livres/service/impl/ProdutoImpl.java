package br.com.livresbs.livres.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.repository.ProdutoRepository;
import br.com.livresbs.livres.service.ProdutoService;

public class ProdutoImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

	@Override
	public List<Produto> listaProdutos() {

		return produtoRepo.findAll();
	}

	@Override
	public Optional<Produto> listaProdutoId(Integer id) {

		return produtoRepo.findById(id);
	}

	@Override
	public Produto cadastrar(Produto produto) {
		return produtoRepo.save(produto);
	}

	@Override
	public Produto editar(Produto produto) {
		return produtoRepo.save(produto);
	}

	@Override
	public void excluir(Produto produto) {
		produtoRepo.delete(produto);

	}

}
