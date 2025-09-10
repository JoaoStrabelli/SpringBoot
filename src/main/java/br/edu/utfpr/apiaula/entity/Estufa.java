package br.edu.utfpr.apiaula.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "estufas")
@Getter
@Setter
public class Estufa extends BaseEntity {
    
    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "estufa", cascade = CascadeType.ALL)
    private List<Sensor> sensores;

    @OneToMany(mappedBy = "estufa", cascade = CascadeType.ALL)
    private List<Controle> controles;
}
