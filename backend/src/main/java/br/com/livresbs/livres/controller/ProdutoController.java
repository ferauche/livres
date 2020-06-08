package br.com.livresbs.livres.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.livresbs.livres.dto.ProdutoDTO;
import br.com.livresbs.livres.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.livresbs.livres.dto.CategoriasProdutosDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.CategoriaProduto;
import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.service.CategoriaService;
import br.com.livresbs.livres.service.ProdutoService;

@RestController
@RequestMapping(value="/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @GetMapping()
    public ProdutosDisponiveisDTO getProdutosDisponiveisVendaByCategoria (
            @RequestHeader(value = "cpf") String cpf,
            @RequestParam(value = "pagina") Integer numeroPagina,
            @RequestParam(value = "categorias", required = false) List<String> categorias
    ) {
        return produtoService.listarProdutosDisponiveisCompraConsumidor(cpf, numeroPagina, categorias);
    }

    @CrossOrigin
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastraProduto(@RequestBody ProdutoDTO produto) {
    	return produtoService.cadastrar(produto);
    }

    @CrossOrigin
    @GetMapping("/listar")
    public List<ProdutoDTO> listaProdutos(@RequestBody ProdutoDTO produto) {
        return produtoService.listaProdutos();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaProduto(@PathVariable(value = "id") Integer id){
        return produtoService.deletarProduto(id);
    }


}
