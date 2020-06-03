package br.com.livresbs.livres.dto;

import br.com.livresbs.livres.model.MetodoPagamento;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CheckoutDTO {

    private List<ProdutoCompradoDTO> produtos;
    private Double valorTotal;
    private List<MetodoPagamentoDTO> metodosPagamento;

}
