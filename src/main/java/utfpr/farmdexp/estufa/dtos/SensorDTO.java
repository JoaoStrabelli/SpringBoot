package utfpr.farmdexp.estufa.dtos;

import jakarta.validation.constraints.Size;
import utfpr.farmdexp.estufa.models.Sensor;

import java.util.UUID;

public record SensorDTO(
        UUID id,
        @Size(min = 2, max = 200) String tipo
) {

    public static SensorDTO fromEntity(Sensor entity) {
        return new SensorDTO(entity.getId(), entity.getTipo());
    }

}
