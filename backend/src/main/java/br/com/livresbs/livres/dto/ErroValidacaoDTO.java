package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErroValidacaoDTO {

    private Map<String, String> camposInvalidos;

}
