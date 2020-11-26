package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProdutoDisponivelDTO {
    private Long cotacaoId;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private String unidadeMedida;
}
