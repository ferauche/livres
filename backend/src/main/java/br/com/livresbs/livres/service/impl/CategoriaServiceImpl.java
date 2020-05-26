package br.com.livresbs.livres.service.impl;

import br.com.livresbs.livres.model.*;
import br.com.livresbs.livres.repository.CategoriaRepository;
import br.com.livresbs.livres.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    @Override
    public List<Categoria> findAll() {

        return (List<Categoria>) repository.findAll();
    }
}