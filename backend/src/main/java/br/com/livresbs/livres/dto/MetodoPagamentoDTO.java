package br.com.livresbs.livres.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetodoPagamentoDTO {

    private String nome;
    private List<String> meiosPagamento;

}
