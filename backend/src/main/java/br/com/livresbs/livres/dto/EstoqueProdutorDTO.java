package br.com.livresbs.livres.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EstoqueProdutorDTO {

	Integer id ;
	Integer produto_id;
	String produtor_id;
	Integer unidadeMedida_id;
	Integer quantidade;
	BigDecimal preco;
	Integer dataEntrega_id;
	
}
