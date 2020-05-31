package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProdutosDisponiveisDTO {

    private List<ProdutoDisponivelDTO> produtos;

}
