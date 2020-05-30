package br.com.livresbs.livres.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Entity
@Table(name = "tb_produto")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;   
    @NotBlank
	private String nome;
    @NotBlank
	private int categoria;
}
