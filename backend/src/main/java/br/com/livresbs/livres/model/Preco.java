package br.com.livresbs.livres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Preco {

    @Column(name = "produto_id")
    private Integer produtoId;

    @Column(name = "dataentrega_id")
    private Long dataEntregaId;

    private BigDecimal valor;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataentrega_id")
    private DataEntrega dataEntrega;

}
