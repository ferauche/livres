package br.com.livresbs.livres.service;

import br.com.livresbs.livres.dto.CheckoutDTO;
import br.com.livresbs.livres.model.Consumidor;

public interface PedidoService {

    CheckoutDTO checkout(String cpfConsumidor);
    void salvarPedido(String cpfConsumidor);
}
