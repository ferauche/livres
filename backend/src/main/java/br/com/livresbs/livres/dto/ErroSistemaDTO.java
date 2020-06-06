package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErroSistemaDTO {

    private String mensagem;

}
