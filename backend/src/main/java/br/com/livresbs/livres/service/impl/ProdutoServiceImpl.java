package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.dto.ProdutoDTO;
import br.com.livresbs.livres.model.CategoriaProduto;
import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.repository.CategoriaRepository;
import br.com.livresbs.livres.repository.ProdutoRepository;
import br.com.livresbs.livres.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	@Override
	public List<ProdutoDTO> listaProdutos() {

		List<ProdutoDTO> listProdDto = new ArrayList<>();
		produtoRepo.findAll().forEach(produto -> {

			CategoriaProduto categoria = categoriaRepo.findByCategoria(produto.getCategoria()).get();

			ProdutoDTO builderDto = ProdutoDTO.builder()
					.id(produto.getId())
					.nome(produto.getNome())
					.categoria(categoria.getId())
					.build();

			listProdDto.add(builderDto);
		});
		return listProdDto;
	}

	@Override
	public ProdutoDTO listaProdutoId(Integer id) {

		Optional<Produto> prod = produtoRepo.findById(id);

		ProdutoDTO prodDto;
		if(!prod.isPresent()){
			// TODO era pro grupo L1 colocar algo aqui
		}

		CategoriaProduto categoria = categoriaRepo.findByCategoria(prod.get().getCategoria()).get();

		prodDto = ProdutoDTO.builder()
							.id(id)
							.nome(prod.get().getNome())
							.categoria(categoria.getId())
							.build();

		return prodDto;

	}

	public ResponseEntity<String> cadastrar(@RequestBody ProdutoDTO produto) {
		Optional<CategoriaProduto> categoria = categoriaRepo.findById(produto.getCategoria());
	    if(!categoria.isPresent()){
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body("Categoria Não Cadastrada!");
	    }

	    Produto prod = Produto.builder()
	                  .nome(produto.getNome())
	                  .categoria(categoria.get().getCategoria())
	                  .build();

	    produtoRepo.save(prod);
	    return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com Sucesso!");
	}

	@Override
	public Produto editar(Produto produto) {
		return produtoRepo.save(produto);
	}

	@Override
	public void excluir(Produto produto) {
		produtoRepo.delete(produto);
	}

	@Override
	public ResponseEntity<String> deletarProduto(Integer id) {
		if(!produtoRepo.existsById(id)){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria não Encontrada!");
		}

		produtoRepo.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");
	}

}
