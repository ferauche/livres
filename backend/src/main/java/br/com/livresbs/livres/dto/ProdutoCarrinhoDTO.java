package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ProdutoCarrinhoDTO {

    @NotNull
    private Integer estoqueProdutorId;

    @NotNull
    @Min(0)
    private Integer quantidade;

}
