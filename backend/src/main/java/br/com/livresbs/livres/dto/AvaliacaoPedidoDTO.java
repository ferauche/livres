package br.com.livresbs.livres.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class AvaliacaoPedidoDTO {

    @NotBlank(message = "operacao não pode ser nulo")
    private OperacaoAvaliacaoPedido operacao;
    private List<AlteracaoItemCarrinhoDTO> alteracoes;

}