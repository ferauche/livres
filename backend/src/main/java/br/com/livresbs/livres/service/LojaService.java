package br.com.livresbs.livres.service;

import br.com.livresbs.livres.dto.ProdutosDisponiveisDTO;

import java.util.List;

public interface LojaService {

        ProdutosDisponiveisDTO listarProdutosDisponiveisCompraConsumidor(String cpf, Integer pagina, List<String> categorias);

}
