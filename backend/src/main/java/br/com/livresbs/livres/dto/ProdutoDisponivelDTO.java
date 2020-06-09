package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutoDisponivelDTO {
    private Integer estoqueId;
    private String nome;
    private Double preco;
    private String categoria;
    private BigDecimal quantidade;
    private String unidadeMedida;
}
