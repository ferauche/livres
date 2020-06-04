package br.com.livresbs.livres.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.livresbs.livres.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.livresbs.livres.config.properties.ApplicationProperty;
import br.com.livresbs.livres.dto.ProdutoDisponivelDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.CategoriaProduto;
import br.com.livresbs.livres.model.DataEntrega;
import br.com.livresbs.livres.model.EstoqueProdutor;
import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.repository.CategoriaRepository;
import br.com.livresbs.livres.repository.DataEntregaRepository;
import br.com.livresbs.livres.repository.EstoqueProdutorRepository;
import br.com.livresbs.livres.repository.ProdutoRepository;
import br.com.livresbs.livres.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private EstoqueProdutorRepository estoqueProdutorRepository;

	@Autowired
	private ApplicationProperty applicationProperty;

	@Autowired
	private DataEntregaRepository dataEntregaRepository;

	@Override
	public List<ProdutoDTO> listaProdutos() {

		List<ProdutoDTO> listProdDto = new ArrayList<>();
		produtoRepo.findAll().forEach(produto -> {

			ProdutoDTO builderDto = ProdutoDTO.builder()
					.id(produto.getId())
					.nome(produto.getNome())
					.categoria(produto.getCategoria().getId())
					.build();

			listProdDto.add(builderDto);
		});
		return listProdDto;
	}

	@Override
	public Optional<Produto> listaProdutoId(Integer id) {

		return produtoRepo.findById(id);
	}

	public ResponseEntity<String> cadastrar(@RequestBody ProdutoDTO produto) {
		Optional<CategoriaProduto> categoria = categoriaRepo.findById(produto.getCategoria());
	    if(!categoria.isPresent()){
	    	return ResponseEntity.status(HttpStatus.CONFLICT).body("Categoria Não Cadastrada!");
	    }

	    Produto prod = Produto.builder()
	                  .nome(produto.getNome())
	                  .categoria(categoria.get())
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
	public ProdutosDisponiveisDTO listarProdutosDisponiveisCompraConsumidor(
			String cpf,
			Integer pagina,
			List<String> categorias
	) {

		DataEntrega dataEntrega = dataEntregaRepository.encontrarDataEntregaAtivaConsumidor(cpf);

		PageRequest pageRequest = PageRequest.of(
				pagina.intValue() - 1,
				applicationProperty.getQuantidadeIntesPagina()
		);

		Page<EstoqueProdutor> estoques;

		if (null != categorias && !categorias.isEmpty())
			estoques = estoqueProdutorRepository.findByDatasEntregaAndProdutoCategoriaNomeIn(
				dataEntrega,
				categorias,
				pageRequest
			);
		else
			estoques = estoqueProdutorRepository.findByDatasEntrega(dataEntrega, pageRequest);

		List<ProdutoDisponivelDTO> produtos = new ArrayList<>(applicationProperty.getQuantidadeIntesPagina());

		estoques.forEach(estoqueProdutor -> {

			ProdutoDisponivelDTO produtoDisponivel = ProdutoDisponivelDTO.builder()
					.estoqueId(estoqueProdutor.getId())
					.nome(estoqueProdutor.getProduto().getNome())
					.preco(estoqueProdutor.getPreco().doubleValue())
					.categoria(estoqueProdutor.getProduto().getCategoria().getNome())
					.build();

			produtos.add(produtoDisponivel);

		});

		return ProdutosDisponiveisDTO.builder()
				.produtos(produtos)
				.build();

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
