package br.com.livresbs.livres.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;

@Data
@Entity
@Table(name = "tb_estoque_produtor")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueProdutor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;
	
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "produtor_id")
	private Produtor produtor;
	
	@NotBlank
	@ManyToOne
	@JoinColumn(name = "unidademedida_id")
	private UnidadeMedida unidadeMedida;
	
	@NotBlank
	private Integer quantidade;
	
	@NotBlank
	private BigDecimal preco;

	@ManyToMany(mappedBy = "estoques")
	private List<DataEntrega> datasEntrega;

}
