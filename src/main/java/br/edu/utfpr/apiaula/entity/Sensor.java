package br.edu.utfpr.apiaula.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
