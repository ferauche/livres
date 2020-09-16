package br.com.livresbs.livres.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
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

    private String categoria;

    private String unidade;

    private BigDecimal preco;

    private String prazo;

    private LocalDate previsao;

    private String produtor;

    @Column(name = "preco_produtor")
    private BigDecimal precoProdutor;

    @Column(name = "preco_mercado")
    private BigDecimal precoMercado;

    @Column(name = "preco_lojinha")
    private BigDecimal precoLojinha;

    private Integer mensal;

    @OneToMany(mappedBy = "produto")
    private Preco precoPreComunidade;

}
