package br.com.livresbs.livres.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroValidacaoRequestDTO {

    private List<ErroCampoRequestDTO> body;
    private ErroCampoRequestDTO header;
    private ErroCampoRequestDTO param;

}
