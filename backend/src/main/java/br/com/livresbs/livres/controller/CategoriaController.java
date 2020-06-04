package br.com.livresbs.livres.controller;

import br.com.livresbs.livres.dto.CategoriasProdutosDTO;
import br.com.livresbs.livres.dto.ProdutoDTO;
import br.com.livresbs.livres.model.CategoriaProduto;
import br.com.livresbs.livres.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categoria")
    public List<CategoriaProduto> getCategorias() {
        /*
        List<CategoriaProduto> categorias = categoriaService.findAll();

        return CategoriasProdutosDTO.builder()
                .categorias(categorias.stream().map(CategoriaProduto::getNome).collect(Collectors.toList()))
                .build();*/

        return categoriaService.findAll();
    }


    @PostMapping("/categoria")
    public ResponseEntity<String> cadastraCategoria(@RequestBody CategoriaProduto categoria) {
        return categoriaService.cadastraCategoria(categoria);
    }

    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<String> deletaConsumidor(@PathVariable(value = "id") Integer id) {
        return categoriaService.deletarCategoria(id);
    }

}
