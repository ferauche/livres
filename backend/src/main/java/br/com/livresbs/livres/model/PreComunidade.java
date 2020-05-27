package br.com.livresbs.livres.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_precomunidade")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreComunidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String Nome;

    @OneToMany(mappedBy = "precomunidade")
    @JsonManagedReference
    private List<Consumidor> consumidor;
    
}
