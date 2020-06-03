package br.com.livresbs.livres.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetodoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    String nome;

    @ManyToMany
    @JoinTable(
            name = "metodo_meio",
            joinColumns = @JoinColumn(name = "metodo_id"),
            inverseJoinColumns = @JoinColumn(name = "meio_id")
    )
    private List<MeioPagamento> meiosPagamento;

}
