package utfpr.farmdexp.estufa.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import utfpr.farmdexp.estufa.dtos.EstufaDTO;
import utfpr.farmdexp.estufa.services.EstufaService;

@RestController
@RequestMapping("/estufa")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Estufas", description = "Endpoints para gerenciar estufas")
public class EstufaController {

    private final EstufaService service;

    public EstufaController(EstufaService service) {
        this.service = service;
    }

    @Operation(summary = "Criar estufa para ambiente", description = "Cria uma nova estufa associada a um ambiente existente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = EstufaDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Ambiente não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping("/ambiente/{ambienteId}")
    public EstufaDTO criar(@PathVariable String ambienteId, @Valid @RequestBody EstufaDTO dto) {
        return service.salvar(ambienteId, dto);
    }

    @Operation(summary = "Listar estufas por ambiente", description = "Lista as estufas associadas a um ambiente. Tem suporte à paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = EstufaDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Ambiente não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/ambiente/{ambienteId}")
    public Page<EstufaDTO> listarPorAmbiente(
            @PathVariable String ambienteId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorAmbiente(ambienteId, pagina, tamanho);
    }

    @Operation(summary = "Listar estufas", description = "Lista estufas com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = EstufaDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<EstufaDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @Operation(summary = "Obter estufa por ID", description = "Retorna uma estufa específica pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = EstufaDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Estufa não encontrada"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public EstufaDTO obterPorId(@PathVariable String id) {
        return EstufaDTO.fromEntity(service.buscarPorId(id));
    }

    @Operation(summary = "Atualizar estufa", description = "Atualiza os dados de uma estufa existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = EstufaDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Estufa não encontrada"),
            @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public EstufaDTO atualizar(@PathVariable String id, @Valid @RequestBody EstufaDTO dto) {
        return EstufaDTO.fromEntity(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar estufa", description = "Remove uma estufa existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estufa não encontrada"),
            @ApiResponse(responseCode = "500")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}