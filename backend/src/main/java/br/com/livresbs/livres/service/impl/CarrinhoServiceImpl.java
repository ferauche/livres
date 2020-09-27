package br.com.livresbs.livres.service.impl;


import br.com.livresbs.livres.dto.CarrinhoDTO;
import br.com.livresbs.livres.dto.ProdutoCarrinhoDTO;
import br.com.livresbs.livres.model.Carrinho;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.model.Cotacao;
import br.com.livresbs.livres.repository.CarrinhoRepository;

import br.com.livresbs.livres.repository.ConsumidorRepository;
import br.com.livresbs.livres.repository.CotacaoRepository;
import br.com.livresbs.livres.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {


    @Autowired
    private CarrinhoRepository repositoryCarrinho;

    @Autowired
    private ConsumidorRepository repositoryConsumidor;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    /*
        TODO implementar para que o parâmetro "quantidade" seja Double, pois existem medidas de vendas que podem conter
         valores decimais, por exemplo: KG. Implementar validação para verificar se é permitido a gravação do Double no
         formato enviado (real ou inteiro) de acordo com o tipo de unidade de medida associado ao estoque.
     */
    @Override
    public void sincronizarProduto(String cpf, Long cotacaoId, Integer quantidade) {
        Optional<Carrinho> carrinhoOptional = repositoryCarrinho.findByConsumidorCpfAndCotacaoId(cpf, cotacaoId);
        if(carrinhoOptional.isPresent()){

            Carrinho carrinho = carrinhoOptional.get();
            Optional<Cotacao> cotacaoOptional = cotacaoRepository.findById(cotacaoId);
            Cotacao cotacao = cotacaoOptional.get();

            if (carrinho.getQuantidade() > 0) {
                carrinho.setQuantidade(quantidade);
                repositoryCarrinho.save(carrinho);
            } else {
                repositoryCarrinho.delete(carrinho);
            }

        } else{

            Optional<Consumidor> consumidorOptional = repositoryConsumidor.findById(cpf);

            if(!consumidorOptional.isPresent()){
                //TODO Lançar erro quando consumidor não existir
            }

            Optional<Cotacao> cotacaoOptional = cotacaoRepository.findById(cotacaoId);

            if(!cotacaoOptional.isPresent()){
                //TODO Lançar erro quando estoque não existir
            }

            if(quantidade > 0) {
                Carrinho carrinho = new Carrinho();
                carrinho.setQuantidade(quantidade);
                carrinho.setConsumidor(consumidorOptional.get());
                carrinho.setCotacao(cotacaoOptional.get());
                repositoryCarrinho.save(carrinho);
            }
        }
    }

    @Override
    public CarrinhoDTO listarCarrinhos(String cpf) {

        List<Carrinho> carrinhos = new ArrayList<>(repositoryCarrinho.findByConsumidorCpf(cpf));
        List<ProdutoCarrinhoDTO> produtos = new LinkedList<ProdutoCarrinhoDTO>();

        carrinhos.forEach(carrinho -> {

            ProdutoCarrinhoDTO produtoCarrinhoDTO = ProdutoCarrinhoDTO.builder()
                    .cotacaoId(carrinho.getCotacao().getId())
                    .quantidade(carrinho.getQuantidade())
                    .build();

            produtos.add(produtoCarrinhoDTO);

        });

        return CarrinhoDTO.builder()
                .produtos(produtos)
                .build();

    }
}
