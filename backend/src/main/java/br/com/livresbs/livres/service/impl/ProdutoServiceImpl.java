package br.com.livresbs.livres.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.livresbs.livres.config.properties.ApplicationProperty;
import br.com.livresbs.livres.dto.ProdutoDisponivelDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.DataEntrega;
import br.com.livresbs.livres.model.EstoqueProdutor;
import br.com.livresbs.livres.repository.DataEntregaRepository;
import br.com.livresbs.livres.repository.EstoqueProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.repository.ProdutoRepository;
import br.com.livresbs.livres.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepo;

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

	@Override
	public ProdutosDisponiveisDTO listarProdutosDisponiveisCompraConsumidor(String cpf, Integer pagina) {

		DataEntrega dataEntrega = dataEntregaRepository.encontrarDataEntregaAtivaConsumidor(cpf);

		Page<EstoqueProdutor> estoques = estoqueProdutorRepository.findByDatasEntrega(
			dataEntrega,
			PageRequest.of(
				pagina.intValue(),
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
