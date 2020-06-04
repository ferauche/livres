package br.com.livresbs.livres.model;

import br.com.livresbs.livres.dto.ConsumidorDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_precomunidade")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PreComunidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String Nome;

    @OneToMany(mappedBy = "precomunidade")
    private List<Consumidor> consumidores;
    
}
