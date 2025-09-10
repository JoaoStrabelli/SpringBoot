package utfpr.farmdexp.estufa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private List<Controle> controles;
}
