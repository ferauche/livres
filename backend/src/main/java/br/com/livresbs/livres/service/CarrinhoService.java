package br.com.livresbs.livres.service;

import br.com.livresbs.livres.dto.ProdutoCarrinhoDTO;
import java.util.List;

public interface CarrinhoService {
    void sincronizarProduto(String cpf, Integer estoqueProdutorId, Integer quantidade);
}
