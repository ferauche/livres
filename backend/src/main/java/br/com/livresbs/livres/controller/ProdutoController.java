package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.CategoriasProdutosDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.CategoriaProduto;
import br.com.livresbs.livres.service.CategoriaService;
import br.com.livresbs.livres.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ProdutosDisponiveisDTO getProdutosDisponiveisVendaByCategoria (
            @RequestHeader(value = "cpf") String cpf,
            @RequestParam(value = "pagina") Integer numeroPagina,
            @RequestParam(value = "categorias", required = false) List<String> categorias
    ) {

        return produtoService.listarProdutosDisponiveisCompraConsumidor(cpf, numeroPagina, categorias);

    }

    @GetMapping("/categorias")
    public CategoriasProdutosDTO getCategorias() {

        List<CategoriaProduto> categorias = categoriaService.findAll();

        return CategoriasProdutosDTO.builder()
                .categorias(categorias.stream().map(CategoriaProduto::getNome).collect(Collectors.toList()))
                .build();

    }

}
