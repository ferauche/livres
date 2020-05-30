package br.com.livresbs.livres.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_consumidor")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Consumidor {

    @Id
    @NotBlank
    private String cpf;

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String senha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precomunidade")
    @NotNull
    @JsonBackReference
    private PreComunidade precomunidade;

	
    
    
}
