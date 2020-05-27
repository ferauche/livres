package br.com.livresbs.livres.model;

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
@Table(name = "tb_data_entrega_estoque_produtor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataEntregaEstoqueProdutor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotBlank
	@ManyToOne
	private DataEntrega dataEntrega;
	
	@NotBlank
	@ManyToOne
	private EstoqueProdutor estoqueProdutor;
	
	@NotBlank
	private Integer ordemConsumo;
}
