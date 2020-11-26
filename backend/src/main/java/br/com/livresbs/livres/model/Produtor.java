package br.com.livresbs.livres.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Entity
@Table(name = "produtores")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produtor {

	@Id
	@NotBlank
	private Integer id;

	@NotBlank
	private String produtor;

}
