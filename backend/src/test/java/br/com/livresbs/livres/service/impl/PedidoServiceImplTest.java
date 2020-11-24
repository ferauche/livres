package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.dto.CheckoutDTO;
import br.com.livresbs.livres.model.Carrinho;
import br.com.livresbs.livres.model.Cotacao;
import br.com.livresbs.livres.model.MetodoPagamento;
import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.repository.CarrinhoRepository;
import br.com.livresbs.livres.repository.MetodoPagamentoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceImplTest {

    @Mock
    private CarrinhoRepository carrinhoRepository;

    @Mock
    private MetodoPagamentoRepository metodoPagamentoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoServiceImpl;

    @Test
    void checkout() {
        Produto produto1 = new Produto();
        produto1.setNome("Batata");

        Cotacao cotacao1 = new Cotacao();
        cotacao1.setProduto(produto1);
        cotacao1.setPreco(BigDecimal.valueOf(3.50));

        Carrinho carrinho1 = new Carrinho();
        carrinho1.setQuantidade(3.0);
        carrinho1.setCotacao(cotacao1);

        Produto produto2 = new Produto();
        produto2.setNome("Banana");

        Cotacao cotacao2 = new Cotacao();
        cotacao2.setProduto(produto2);
        cotacao2.setPreco(BigDecimal.valueOf(2.00));

        Carrinho carrinho2 = new Carrinho();
        carrinho2.setQuantidade(5.0);
        carrinho2.setCotacao(cotacao2);

        Produto produto3 = new Produto();
        produto3.setNome("Goiaba");

        Cotacao cotacao3 = new Cotacao();
        cotacao3.setProduto(produto3);
        cotacao3.setPreco(BigDecimal.valueOf(6.25));

        Carrinho carrinho3 = new Carrinho();
        carrinho3.setQuantidade(2.0);
        carrinho3.setCotacao(cotacao3);

        List<Carrinho> lista = new ArrayList<>();
        lista.add(carrinho1);
        lista.add(carrinho2);
        lista.add(carrinho3);

        List<MetodoPagamento> lista2 = new ArrayList<>();

        Mockito.when(carrinhoRepository.findByConsumidorCpf("123.456.789-10")).thenReturn(lista);
        Mockito.when(metodoPagamentoRepository.findAll()).thenReturn(lista2);

        CheckoutDTO checkoutdto = pedidoServiceImpl.checkout("123.456.789-10");

        Assertions.assertTrue(checkoutdto.getValorTotal().equals(new BigDecimal(33.00).setScale(2)));
    }
}