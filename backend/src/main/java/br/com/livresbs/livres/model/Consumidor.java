package br.com.livresbs.livres.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_consumidor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @NotNull
    @JsonBackReference
    private PreComunidade precomunidade;

	
    
    
}
