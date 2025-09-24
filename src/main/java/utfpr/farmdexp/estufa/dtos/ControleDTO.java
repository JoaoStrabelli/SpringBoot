package utfpr.farmdexp.estufa.dtos;

import jakarta.validation.constraints.Size;
import utfpr.farmdexp.estufa.models.Controle;

import java.util.UUID;

public record ControleDTO(
    UUID id,
    @Size(min = 2, max = 200) String tipo
) {

    public static ControleDTO fromEntity(Controle entity) {
        return new ControleDTO(entity.getId(), entity.getTipo());
    }

}