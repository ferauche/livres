package br.com.livresbs.livres.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @ManyToOne
    @JoinColumn(name = "categoria_id")
	private CategoriaProduto categoria;
}
