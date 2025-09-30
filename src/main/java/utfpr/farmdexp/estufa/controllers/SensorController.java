package utfpr.farmdexp.estufa.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import utfpr.farmdexp.estufa.dtos.ControleDTO;
import utfpr.farmdexp.estufa.dtos.SensorDTO;
import utfpr.farmdexp.estufa.services.SensorService;

@RestController
@RequestMapping("/sensor")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Sensores", description = "Endpoints para gerenciar sensores")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @Operation(summary = "Criar sensor para controle", description = "Cria um novo controle associado a um controle existente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = SensorDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Controle não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping("/controle/{controleId}")
    public SensorDTO criar(@PathVariable String controleId, @Valid @RequestBody SensorDTO dto) {
        return service.salvar(controleId, dto);
    }

    @Operation(summary = "Listar sensores por controle", description = "Lista os sensores associados a um controle. Tem suporte à paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = SensorDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Controle não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/controle/{controleId}")
    public Page<SensorDTO> listarPorControle(
            @PathVariable String controleId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorControle(controleId, pagina, tamanho);
    }

    @Operation(summary = "Listar sensores", description = "Lista sensores com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = SensorDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<SensorDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @Operation(summary = "Obter sensor por ID", description = "Retorna um sensor específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = SensorDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public SensorDTO obterPorId(@PathVariable String id) {
        return SensorDTO.fromEntity(service.buscarPorId(id));
    }

    @Operation(summary = "Atualizar sensor", description = "Atualiza os dados de um sensor existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = SensorDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public SensorDTO atualizar(@PathVariable String id, @Valid @RequestBody SensorDTO dto) {
        return SensorDTO.fromEntity(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar sensor", description = "Remove um sensor existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Sensor não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}