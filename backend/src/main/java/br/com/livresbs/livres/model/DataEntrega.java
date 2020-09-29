package br.com.livresbs.livres.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_data_entrega")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataEntrega {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "precomunidade_id")
	private PreComunidade precomunidade;
	
	@NotNull
	@Column(columnDefinition = "DATE")
	private LocalDate prazo;
	
	@NotNull
	@Column(columnDefinition = "DATE")
	private LocalDate prazoLimitePedido;

	@OneToMany(mappedBy = "dataEntrega")
    private List<Cotacao> cotacao;
	
}
