package br.com.livresbs.livres.service.impl;


import br.com.livresbs.livres.model.Carrinho;
import br.com.livresbs.livres.model.Consumidor;
import br.com.livresbs.livres.model.EstoqueProdutor;
import br.com.livresbs.livres.repository.CarrinhoRepository;

import br.com.livresbs.livres.repository.ConsumidorRepository;
import br.com.livresbs.livres.repository.EstoqueProdutorRepository;
import br.com.livresbs.livres.service.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {


    @Autowired
    private CarrinhoRepository repositoryCarrinho;

    @Autowired
    private ConsumidorRepository repositoryConsumidor;

    @Autowired
    private EstoqueProdutorRepository repositoryEstoqueProdutor;


    @Override
    public void sincronizarProduto(String cpf, Integer estoqueProdutorId, Integer quantidade) {
        Optional<Carrinho> carrinhoOptional = repositoryCarrinho.findByConsumidorCpfAndEstoqueProdutorId(cpf, estoqueProdutorId);
        if(carrinhoOptional.isPresent()){
            Carrinho carrinho = carrinhoOptional.get();
            if (carrinho.getQuantidade() > 0) {
                carrinho.setQuantidade(quantidade);
                repositoryCarrinho.save(carrinho);
            } else {
                repositoryCarrinho.delete(carrinho);
            }
        } else {
            Optional<Consumidor> consumidorOptional = repositoryConsumidor.findById(cpf);
            if(!consumidorOptional.isPresent()){
                //TODO Lançar erro quando consumidor não existir
            }
            Optional<EstoqueProdutor> estoqueProdutorOptional = repositoryEstoqueProdutor.findById(estoqueProdutorId);
            if(!estoqueProdutorOptional.isPresent()){
                //TODO Lançar erro quando estoque não existir
            }
            Carrinho carrinho = new Carrinho();
            carrinho.setQuantidade(quantidade);
            carrinho.setConsumidor(consumidorOptional.get());
            carrinho.setEstoqueProdutor(estoqueProdutorOptional.get());
        }
    }
}
