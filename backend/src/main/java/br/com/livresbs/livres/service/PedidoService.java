package br.com.livresbs.livres.service;

import br.com.livresbs.livres.dto.CheckoutDTO;

public interface PedidoService {

    CheckoutDTO checkout(String cpfConsumidor);

}
