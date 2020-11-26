package br.com.livresbs.livres.service;

import br.com.livresbs.livres.dto.AvaliacaoPedidoDTO;
import br.com.livresbs.livres.dto.CheckoutDTO;
import br.com.livresbs.livres.dto.FinalizarPedidoDTO;
import br.com.livresbs.livres.dto.PedidoDTO;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.model.StatusPedido;

public interface PedidoService {

    CheckoutDTO checkout(String cpfConsumidor);
    void salvarPedido(String cpfConsumidor, FinalizarPedidoDTO body);
    PedidoDTO consultarPedido(StatusPedido status);
    void avaliarPedido(Long idPedido, AvaliacaoPedidoDTO avaliacao);
}
