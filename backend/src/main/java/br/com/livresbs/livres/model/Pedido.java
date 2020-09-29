package br.com.livresbs.livres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private BigDecimal valorTotal;

    @Enumerated(EnumType.ORDINAL)
    private StatusPedido status = StatusPedido.CRIADO;

    @ManyToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itemPedidos;

    @ManyToOne
    @JoinColumn(name = "metodo_id")
    private MetodoPagamento metodoPagamento;

    @ManyToOne
    @JoinColumn(name = "meio_id")
    private MeioPagamento meioPagamento;

    @ManyToOne
    @JoinColumn(name = "enderecoentrega_id")
    private EnderecoEntrega enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "tipoentrega_id")
    private TipoEntrega tipoEntrega;

}
