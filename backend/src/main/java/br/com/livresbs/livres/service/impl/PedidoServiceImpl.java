package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.config.properties.MessageProperty;
import br.com.livresbs.livres.dto.FinalizarPedidoDTO;
import br.com.livresbs.livres.dto.MetodoPagamentoDTO;
import br.com.livresbs.livres.dto.PedidoDTO;
import br.com.livresbs.livres.dto.ProdutoCompradoDTO;
import br.com.livresbs.livres.dto.CheckoutDTO;
import br.com.livresbs.livres.exception.CarrinhoVazioException;
import br.com.livresbs.livres.model.*;
import br.com.livresbs.livres.repository.CarrinhoRepository;
import br.com.livresbs.livres.repository.ConsumidorRepository;
import br.com.livresbs.livres.repository.EnderecoEntregaRepository;
import br.com.livresbs.livres.repository.ItemPedidoRepository;
import br.com.livresbs.livres.repository.MetodoPagamentoRepository;
import br.com.livresbs.livres.repository.PedidoRepository;
import br.com.livresbs.livres.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private EnderecoEntregaRepository enderecoEntregaRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

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
    @Transactional
    public void salvarPedido(String cpfConsumidor, FinalizarPedidoDTO body) {
        EnderecoEntrega endereco = new EnderecoEntrega();
        endereco.setCEP(body.getCep());
        endereco.setCidade(body.getCidade());
        endereco.setEstado(body.getEstado());
        endereco.setEndereco(body.getEndereco());
        endereco.setComplemento(body.getEndereco());
        endereco.setNumero(body.getNumero());

        enderecoEntregaRepository.save(endereco);

        List<Carrinho> carrinhos = carrinhoRepository.findByConsumidorCpf(cpfConsumidor);
        Optional<Consumidor> consumidorOptional = consumidorRepository.findById(cpfConsumidor);
        Consumidor consumidor = consumidorOptional.get();
        consumidor.getEnderecos().add(endereco);
        consumidorRepository.save(consumidor);
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<Carrinho> itemsCarrinho = carrinhoRepository.findByConsumidorCpf(cpfConsumidor);
        for (Carrinho itemCarrinho : itemsCarrinho) {
            valorTotal = valorTotal.add(
                    itemCarrinho.getCotacao().getPreco().multiply(
                            new BigDecimal(itemCarrinho.getQuantidade())
                    )
            );

        }

        MetodoPagamento metodoPagamento = metodoPagamentoRepository.findByNome(body.getMetodoPagamento()).get();

        Pedido pedido = new Pedido();
        pedido.setMetodoPagamento(metodoPagamento);
        pedido.setMeioPagamento(
            metodoPagamento.getMeiosPagamento()
                .stream()
                .filter(meioPagamento -> meioPagamento.getNome().equals(body.getMeioPagamento()))
                .collect(Collectors.toList())
                .get(0)
        );
        pedido.setValorTotal(valorTotal);
        pedido.setConsumidor(consumidor);
        pedido.setEnderecoEntrega(endereco);
        pedidoRepository.save(pedido);

        carrinhos.forEach(carrinho -> {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setCotacao(carrinho.getCotacao());
            itemPedido.setQuantidade(carrinho.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedidoRepository.save(itemPedido);
        });

        carrinhoRepository.deleteByConsumidorCpf(cpfConsumidor);
    }

    @Override
    public PedidoDTO consultarPedido(StatusPedido status){
        List<Pedido> pedidos = pedidoRepository.findByStatus(status);
        return PedidoDTO.builder()
                .pedidos(pedidos)
                .build();
    }

    @Override
    public void alterarStatusPedido(Long id, StatusPedido statusPedido) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        Pedido pedido = pedidoOptional.get();
        pedido.setStatus(statusPedido);
        pedidoRepository.save(pedido);
    }
}
