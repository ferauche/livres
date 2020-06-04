package br.com.livresbs.livres.service;

import java.util.List;
import br.com.livresbs.livres.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface CategoriaService {
    List<CategoriaProduto> findAll();

    ResponseEntity<String> cadastraCategoria(@RequestBody CategoriaProduto cat);

    ResponseEntity<String> deletarCategoria(Integer id);
};

