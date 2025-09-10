package br.edu.utfpr.apiaula.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ambientes")
@Getter
@Setter
public class Ambiente extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "ambiente")
    private List<Sensor> sensores;
}
