package br.edu.utfpr.apiaula.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "controles")
@Getter
@Setter
public class Controle extends BaseEntity {

    @Column(nullable = false)
    private String tipo; // Exemplo: ventilação, irrigação

    @ManyToOne
    @JoinColumn(name = "estufa_id", nullable = false)
    private Estufa estufa;

    @ManyToMany
    @JoinTable(
      name = "controle_sensor", 
      joinColumns = @JoinColumn(name = "controle_id"), 
      inverseJoinColumns = @JoinColumn(name = "sensor_id"))
    private List<Sensor> sensores;
}
