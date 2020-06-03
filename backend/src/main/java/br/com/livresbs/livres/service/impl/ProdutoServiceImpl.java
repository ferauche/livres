package br.com.livresbs.livres.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import br.com.livresbs.livres.model.Consumidor;
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
	public List<Produto> listaProdutos() {

		return produtoRepo.findAll();
	}

	@Override
	public Optional<Produto> listaProdutoId(Integer id) {

		return produtoRepo.findById(id);
	}

	@Override
	public ResponseEntity<String> cadastrar(@RequestBody Produto produto) {
		/*System.out.println("Prestes a criar o produto");
	            Optional<CategoriaProduto> categoria = categoriaRepo.findById(produto.getCategoria().getId());
	            if(!categoria.isPresent()){
	                //TODO tratamente caso categoria nao exista
	            }
	            System.out.println("Prestes a criar o produto");
	            Produto prod = Produto.builder()
	                    .nome(produto.getNome())
	                    .categoria(categoria.get())
	                    .build();
	            
	            produtoRepo.save(prod);*/
		produtoRepo.save(produto);

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
	public ProdutosDisponiveisDTO listarProdutosDisponiveisCompraConsumidor(String cpf, Integer pagina) {

		DataEntrega dataEntrega = dataEntregaRepository.encontrarDataEntregaAtivaConsumidor(cpf);

		Page<EstoqueProdutor> estoques = estoqueProdutorRepository.findByDatasEntrega(
			dataEntrega,
			PageRequest.of(
				pagina.intValue() - 1,
				applicationProperty.getQuantidadeIntesPagina()
			)
		);

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

}
