package br.com.livresbs.livres.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.livresbs.livres.service.CategoriaService;

import java.util.HashMap;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController("/produtos")
public class ProdutoController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public String getProdutosDisponiveisVenda(
            @Valid @NotNull(message = "Id do consumidor é obrigatório") @RequestHeader Long consumidorId,
            @Valid @NotNull(message = "Número da pagina é obrigatório") @RequestParam Integer numeroPagina
    ) {

        return "foi";

    }

    @GetMapping("/categorias")
    public CategoriasProdutosDTO getCategorias(){
        var categorias = (List<Categoria>) categoriaService.findAll();
        var params = new HashMap<String, Object>();
        params.put("categorias", categorias);
        return new CategoriasProdutosDTO()
    }
}
