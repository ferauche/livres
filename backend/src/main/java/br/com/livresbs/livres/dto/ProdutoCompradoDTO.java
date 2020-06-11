package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoCompradoDTO {

    private String nome;
    private Double preco;
    private Double quantidade;

}
