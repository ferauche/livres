package br.com.livresbs.livres.dto;

import lombok.Data;

@Data
public class FinalizarPedidoDTO {

    private String destinatario;
    private Integer cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String endereco;
    private Integer numero;
    private String complemento;

    private String metodoPagamento;
    private String meioPagamento;

}
