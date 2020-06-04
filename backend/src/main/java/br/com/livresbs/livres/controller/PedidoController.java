package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.CheckoutDTO;
import br.com.livresbs.livres.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/checkout")
    public CheckoutDTO checkout(@RequestHeader("cpf") String cpf) {
        return pedidoService.checkout(cpf);
    }

}
