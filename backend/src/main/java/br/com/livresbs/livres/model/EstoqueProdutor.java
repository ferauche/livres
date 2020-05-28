package br.com.livresbs.livres.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_estoque_produtor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueProdutor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	@ManyToOne
	private Produto produto;
	
	@NotBlank
	@ManyToOne
	private Produtor produtor;
	
	@NotBlank
	@ManyToOne
	private UnidadeMedida unidadeMedida;
	
	@NotBlank
	private Integer quantidade;
	
	@NotBlank
	private BigDecimal preco;
}
