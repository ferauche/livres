package br.com.livresbs.livres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
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
    private StatusPedido status = StatusPedido.PENDENTE_PAGAMENTO;

    @ManyToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

    @ManyToMany
    @JoinTable(
        name = "pedido_estoqueprodutor",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "estoqueprodutor_id")
    )
    private List<EstoqueProdutor> estoques;

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
