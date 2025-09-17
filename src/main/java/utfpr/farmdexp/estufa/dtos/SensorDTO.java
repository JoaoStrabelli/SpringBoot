package utfpr.farmdexp.estufa.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record SensorDTO(UUID id,
        @NotBlank
        @Size(min = 2, max = 200)
        String tipo
) {}
