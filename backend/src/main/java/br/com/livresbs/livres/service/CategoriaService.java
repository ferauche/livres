package br.com.livresbs.livres.service;

import java.util.List;
import br.com.livresbs.livres.model.*;

public interface CategoriaService {
    List<CategoriaProduto> findAll();
}