package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.config.properties.MessageProperty;
import br.com.livresbs.livres.dto.MetodoPagamentoDTO;
import br.com.livresbs.livres.dto.ProdutoCompradoDTO;
import br.com.livresbs.livres.dto.CheckoutDTO;
import br.com.livresbs.livres.exception.CarrinhoVazioException;
import br.com.livresbs.livres.model.*;
import br.com.livresbs.livres.repository.CarrinhoRepository;
import br.com.livresbs.livres.repository.ConsumidorRepository;
import br.com.livresbs.livres.repository.MetodoPagamentoRepository;
import br.com.livresbs.livres.repository.PedidoRepository;
import br.com.livresbs.livres.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ConsumidorRepository consumidorRepository;

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
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Carrinho itemCarrinho : itemsCarrinho) {

            produtos.add(
                ProdutoCompradoDTO.builder()
                    .nome(itemCarrinho.getCotacao().getProduto().getNome())
                    .preco(itemCarrinho.getCotacao().getPreco())
                    .quantidade(itemCarrinho.getQuantidade().doubleValue())
                    .build()
            );

            valorTotal = valorTotal.add(
                    itemCarrinho.getCotacao().getPreco().multiply(
                            new BigDecimal(itemCarrinho.getQuantidade())
                    )
            );

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

    @Override
    public void salvarPedido(String cpfConsumidor) {
        List<Carrinho> carrinhos = carrinhoRepository.findByConsumidorCpf(cpfConsumidor);
        Optional<Consumidor> consumidorOptional = consumidorRepository.findById(cpfConsumidor);
        Consumidor consumidor = consumidorOptional.get();
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<Carrinho> itemsCarrinho = carrinhoRepository.findByConsumidorCpf(cpfConsumidor);
        for (Carrinho itemCarrinho : itemsCarrinho) {
            valorTotal = valorTotal.add(
                    itemCarrinho.getCotacao().getPreco().multiply(
                            new BigDecimal(itemCarrinho.getQuantidade())
                    )
            );

        }
        Pedido pedido = new Pedido();
        pedido.setValorTotal(valorTotal);
        pedido.setConsumidor(consumidor);
        pedido.setEnderecoEntrega(consumidor.getEnderecos().get(0));
        List<ItemPedido> itemPedidos = new LinkedList<>();
        carrinhos.forEach(carrinho -> {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setCotacao(carrinho.getCotacao());
            itemPedido.setQuantidade(carrinho.getQuantidade());
            itemPedidos.add(itemPedido);
        });
        pedido.setItemPedidos(itemPedidos);
        pedidoRepository.save(pedido);
    }
}
