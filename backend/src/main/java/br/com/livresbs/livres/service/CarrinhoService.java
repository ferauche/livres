package br.com.livresbs.livres.service;

import br.com.livresbs.livres.dto.CarrinhoDTO;
import br.com.livresbs.livres.dto.ProdutoCarrinhoDTO;
import java.util.List;

public interface CarrinhoService {
    void sincronizarProduto(String cpf, Long cotacaoId, Integer quantidade);
    CarrinhoDTO listarCarrinhos(String cpf);
}
