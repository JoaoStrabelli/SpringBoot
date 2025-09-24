package utfpr.farmdexp.estufa.dtos;

import java.util.UUID;
import utfpr.farmdexp.estufa.models.Ambiente;

public record AmbienteDTO(
    UUID id,
    String nome
) {

    public static AmbienteDTO fromEntity(Ambiente entity) {
        return new AmbienteDTO(entity.getId(), entity.getNome());
    }

}