package utfpr.farmdexp.estufa.dtos;

import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public record AmbienteDTO(
    UUID id,
    @jakarta.validation.constraints.NotBlank(message = "nome é obrigatório") String nome
) {}