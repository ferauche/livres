package br.com.livresbs.livres.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Entity
@Table(name = "tb_produtor")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produtor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank
	private String nome;
	@NotBlank
	private String cnpj;

}
