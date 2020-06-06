package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.service.LojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loja")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    @GetMapping("/produtos")
    public ProdutosDisponiveisDTO getProdutosDisponiveisVendaByCategoria (
            @RequestHeader(value = "cpf") String cpf,
            @RequestParam(value = "pagina", required = false) Integer numeroPagina,
            @RequestParam(value = "categorias", required = false) List<String> categorias
    ) {

        if (numeroPagina == null || numeroPagina < 1)
            numeroPagina = 1;

        return lojaService.listarProdutosDisponiveisCompraConsumidor(cpf, numeroPagina, categorias);

    }

}
