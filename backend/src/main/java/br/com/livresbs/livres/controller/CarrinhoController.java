package br.com.livresbs.livres.controller;


import br.com.livresbs.livres.dto.ProdutoCarrinhoDTO;
import br.com.livresbs.livres.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping
    public void sincronizarProduto(
            @RequestHeader(value = "cpf") String cpf,
            @Valid @RequestBody ProdutoCarrinhoDTO produtoCarrinhoDTO
    ) {
        carrinhoService.sincronizarProduto(
                cpf,
                produtoCarrinhoDTO.getEstoqueProdutorId(),
                produtoCarrinhoDTO.getQuantidade()
        ) ;
    }

}
