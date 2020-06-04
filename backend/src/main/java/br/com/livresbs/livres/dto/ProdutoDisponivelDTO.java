package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDisponivelDTO {
    private Integer estoqueId;
    private String nome;
    private Double preco;
    private String categoria;
}
