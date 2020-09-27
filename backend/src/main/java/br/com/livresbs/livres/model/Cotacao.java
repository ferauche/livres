package br.com.livresbs.livres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cotacao {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal preco;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "dataentrega_id")
    private DataEntrega dataEntrega;

}
