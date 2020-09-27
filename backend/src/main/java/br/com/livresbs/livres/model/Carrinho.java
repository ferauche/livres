package br.com.livresbs.livres.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "cotacao_id")
    private Cotacao cotacao;

    @ManyToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

}
