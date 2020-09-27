package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutoCompradoDTO {

    private String nome;
    private BigDecimal preco;
    private Double quantidade;

}
