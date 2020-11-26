package br.com.livresbs.livres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @OneToOne
    @JoinColumn(name = "cotacao_id")
    private Cotacao cotacao;

}
