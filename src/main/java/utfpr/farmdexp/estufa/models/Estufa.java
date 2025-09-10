package utfpr.farmdexp.estufa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "estufas")
@Getter
@Setter
public class Estufa extends BaseEntity {
    
    @Column(nullable = false)
    private String nome;

    // FIXME
//    @OneToMany(mappedBy = "estufa", cascade = CascadeType.ALL)
//    private List<Estufa> estufas;

}
