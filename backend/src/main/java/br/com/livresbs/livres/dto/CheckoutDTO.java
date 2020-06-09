package br.com.livresbs.livres.dto;

import br.com.livresbs.livres.model.MetodoPagamento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class CheckoutDTO {

    private List<ProdutoCompradoDTO> produtos;
    private BigDecimal valorTotal;
    private List<MetodoPagamentoDTO> metodosPagamento;

}
