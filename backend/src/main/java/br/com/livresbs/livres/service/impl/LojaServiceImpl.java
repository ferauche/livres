package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.config.properties.ApplicationProperty;
import br.com.livresbs.livres.dto.ProdutoDisponivelDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.Cotacao;
import br.com.livresbs.livres.model.DataEntrega;
import br.com.livresbs.livres.repository.CotacaoRepository;
import br.com.livresbs.livres.repository.DataEntregaRepository;
import br.com.livresbs.livres.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LojaServiceImpl implements LojaService {

    @Autowired
    private DataEntregaRepository dataEntregaRepository;

    @Autowired
    private CotacaoRepository cotacaoRepository;

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

        Page<Cotacao> cotacoes;

        if (Objects.nonNull(categorias) && !categorias.isEmpty())
            cotacoes = cotacaoRepository.findByDatasEntregaAndProdutoCategoriaIn(
                    dataEntrega,
                    categorias,
                    pageRequest
            );
        else
            cotacoes = cotacaoRepository.findByDatasEntrega(dataEntrega, pageRequest);

        List<ProdutoDisponivelDTO> produtos = new ArrayList<>(applicationProperty.getQuantidadeIntesPagina());

        cotacoes.forEach(cotacao -> {

            ProdutoDisponivelDTO produtoDisponivel = ProdutoDisponivelDTO.builder()
                    .cotacaoId(cotacao.getId())
                    .nome(cotacao.getProduto().getNome())
                    .preco(cotacao.getPreco())
                    .categoria(cotacao.getProduto().getCategoria())
                    .unidadeMedida(cotacao.getProduto().getUnidade())
                    .build();

            produtos.add(produtoDisponivel);

        });

        return ProdutosDisponiveisDTO.builder()
                .produtos(produtos)
                .paginaAtual(pagina)
                .totalPaginas(cotacoes.getTotalPages())
                .build();

    }

}
