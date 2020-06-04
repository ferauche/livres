package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.model.*;
import br.com.livresbs.livres.repository.CategoriaRepository;
import br.com.livresbs.livres.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CategoriaImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<CategoriaProduto> findAll() {
        return repository.findAll();
    }

    @Override
    public ResponseEntity<String> cadastraCategoria(@RequestBody CategoriaProduto cat) {
        repository.save(cat);

        return ResponseEntity.status(HttpStatus.OK).body("Cadastrado com Sucesso!");

    }

    @Override
    public ResponseEntity<String> deletarCategoria(Integer id){

        if(!repository.existsById(id)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Categoria não Encontrada!");
        }

        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso!");
    }


}