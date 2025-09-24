package utfpr.farmdexp.estufa.dtos;

import jakarta.validation.constraints.Size;
import utfpr.farmdexp.estufa.models.Estufa;

import java.util.UUID;

public record EstufaDTO(
    UUID id,
    @Size(min = 2, max = 200)
    String nome
) {

    public static EstufaDTO fromEntity(Estufa entity) {
        return new EstufaDTO(entity.getId(), entity.getNome());
    }

}