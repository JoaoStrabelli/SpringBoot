package utfpr.farmdexp.estufa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ambientes")
@Getter
@Setter
public class Ambiente extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "ambiente")
    private List<Estufa> estufas;
}
