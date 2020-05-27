package br.com.livresbs.livres.dto;

import lombok.Data;

@Data
public class CadastroConsumidorDTO {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;

    private Long id_precomunidade;
}
