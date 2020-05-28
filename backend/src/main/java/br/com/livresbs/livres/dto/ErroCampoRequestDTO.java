package br.com.livresbs.livres.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroCampoRequestDTO {

    private String campo;
    private String valor;
    private String mensagem;

}
