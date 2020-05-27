package br.com.livresbs.livres.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConsumidorDTO {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private Long precomunidade;
}
