package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.config.properties.ApplicationProperty;
import br.com.livresbs.livres.dto.ProdutoDisponivelDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.DataEntrega;
import br.com.livresbs.livres.model.EstoqueProdutor;
import br.com.livresbs.livres.repository.DataEntregaRepository;
import br.com.livresbs.livres.repository.EstoqueProdutorRepository;
import br.com.livresbs.livres.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private DataEntregaRepository dataEntregaRepository;

    @Autowired
    private EstoqueProdutorRepository estoqueProdutorRepository;

    @Autowired
    private ApplicationProperty applicationProperty;

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
                    .quantidade(BigDecimal.valueOf(estoqueProdutor.getQuantidade()).setScale(2, RoundingMode.HALF_UP))
                    .unidadeMedida(estoqueProdutor.getUnidadeMedida().getNome())
                    .build();

            produtos.add(produtoDisponivel);

        });

        return ProdutosDisponiveisDTO.builder()
                .produtos(produtos)
                .paginaAtual(pagina)
                .totalPaginas(estoques.getTotalPages())
                .build();

    }

}
