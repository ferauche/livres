package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.ProdutoDTO;
import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;
import br.com.livresbs.livres.model.Produto;
import br.com.livresbs.livres.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value="produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    public ResponseEntity<String> cadastraProduto(@RequestBody ProdutoDTO produto) {
    	return produtoService.cadastrar(produto);
    }

    @GetMapping
    public List<ProdutoDTO> listaProdutos(@RequestBody ProdutoDTO produto) {
        return produtoService.listaProdutos();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaProduto(@PathVariable(value = "id") Integer id){
        return produtoService.deletarProduto(id);
    }
    
    @PutMapping
    public Produto editaProduto(@RequestBody Produto produto) {
    	return produtoService.editar(produto);
    }


}
