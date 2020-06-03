package br.com.livresbs.livres.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.livresbs.livres.dto.CategoriasProdutosDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.CategoriaProduto;
import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.service.CategoriaService;
import br.com.livresbs.livres.service.ProdutoService;

@RestController
@RequestMapping(value="/api")
public class ProdutoController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public ProdutosDisponiveisDTO getProdutosDisponiveisVendaByCategoria (
            @RequestHeader(value = "cpf") String cpf,
            @RequestParam(value = "pagina") Integer numeroPagina,
            @RequestParam(value = "categorias", required = false) List<String> categorias
    ) {

        return produtoService.listarProdutosDisponiveisCompraConsumidor(cpf, numeroPagina);

    }

    @GetMapping("/categorias")
    public CategoriasProdutosDTO getCategorias() {

        List<CategoriaProduto> categorias = categoriaService.findAll();

        return CategoriasProdutosDTO.builder()
                .categorias(categorias.stream().map(CategoriaProduto::getNome).collect(Collectors.toList()))
                .build();

    }
    
    @PostMapping("/produto")
    public ResponseEntity<String> cadastraProduto(@RequestBody Produto produto) {
    	return this.produtoService.cadastrar(produto);
    }

}
