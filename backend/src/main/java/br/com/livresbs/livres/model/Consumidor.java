package br.com.livresbs.livres.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_consumidor")
@Data
public class Consumidor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String cpf;
    @NotBlank
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precomunidade")
    @NotBlank
    @JsonBackReference
    private PreComunidade precomunidade;
}
