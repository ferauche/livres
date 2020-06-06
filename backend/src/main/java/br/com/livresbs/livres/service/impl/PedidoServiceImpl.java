package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.config.properties.MessageProperty;
import br.com.livresbs.livres.dto.MetodoPagamentoDTO;
import br.com.livresbs.livres.dto.ProdutoCompradoDTO;
import br.com.livresbs.livres.dto.CheckoutDTO;
import br.com.livresbs.livres.exception.CarrinhoVazioException;
import br.com.livresbs.livres.model.Carrinho;
import br.com.livresbs.livres.model.MeioPagamento;
import br.com.livresbs.livres.model.MetodoPagamento;
import br.com.livresbs.livres.repository.CarrinhoRepository;
import br.com.livresbs.livres.repository.MetodoPagamentoRepository;
import br.com.livresbs.livres.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private MessageProperty messageProperty;

    @Autowired
    private MetodoPagamentoRepository metodoPagamentoRepository;

    @Override
    public CheckoutDTO checkout(String cpfConsumidor) {

        List<Carrinho> itemsCarrinho = carrinhoRepository.findByConsumidorCpf(cpfConsumidor);

        if (itemsCarrinho.isEmpty()) {
            throw new CarrinhoVazioException(messageProperty.getMenssagemCarrinhoVazio());
        }

        List<ProdutoCompradoDTO> produtos = new ArrayList<ProdutoCompradoDTO>();
        Double valorTotal = 0.0D;

        for (Carrinho itemCarrinho : itemsCarrinho) {

            produtos.add(
                ProdutoCompradoDTO.builder()
                    .nome(itemCarrinho.getEstoqueProdutor().getProduto().getNome())
                    .preco(itemCarrinho.getEstoqueProdutor().getPreco().doubleValue())
                    .quantidade(itemCarrinho.getQuantidade().doubleValue())
                    .build()
            );

            valorTotal += itemCarrinho.getEstoqueProdutor().getPreco().doubleValue();

        }

        List<MetodoPagamentoDTO> metodosPagamentoDTO = new ArrayList<MetodoPagamentoDTO>();

        metodoPagamentoRepository.findAll().forEach(metodoPagamento -> {
            metodosPagamentoDTO.add(
                MetodoPagamentoDTO.builder()
                    .nome(metodoPagamento.getNome())
                    .meiosPagamento(
                        metodoPagamento.getMeiosPagamento().stream().map(MeioPagamento::getNome).collect(Collectors.toList())
                    )
                    .build()
            );
        });

        return CheckoutDTO.builder()
                .produtos(produtos)
                .valorTotal(valorTotal)
                .metodosPagamento(metodosPagamentoDTO)
                .build();

    }

}
