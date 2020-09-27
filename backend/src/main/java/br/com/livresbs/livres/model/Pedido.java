package br.com.livresbs.livres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    private StatusPedido status = StatusPedido.PENDENTE_PAGAMENTO;

    @ManyToOne
    @JoinColumn(name = "consumidor_id")
    private Consumidor consumidor;

    @ManyToMany
    @JoinTable(
        name = "pedido_cotacoes",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "contacao_id")
    )
    private List<Cotacao> cotacoes;

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
