package utfpr.farmdexp.estufa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sensores")
@Getter
@Setter
public class Sensor extends BaseEntity {

    @Column(nullable = false)
    private String tipo; // Exemplo: temperatura, umidade

    @ManyToOne
    @JoinColumn(name = "estufa_id", nullable = false)
    private Estufa estufa;

    @ManyToOne
    @JoinColumn(name = "ambiente_id", nullable = false)
    private Ambiente ambiente;
}
