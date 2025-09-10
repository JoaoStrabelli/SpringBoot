package utfpr.farmdexp.estufa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ambientes")
@Getter
@Setter
public class Ambiente extends BaseEntity {

    @Column(nullable = false)
    private String nome;

    // FIXME
//    @OneToMany(mappedBy = "ambiente")
//    private List<Ambiente> ambientes;
}
