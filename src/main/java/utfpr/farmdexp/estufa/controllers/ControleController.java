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

import utfpr.farmdexp.estufa.dtos.AmbienteDTO;
import utfpr.farmdexp.estufa.dtos.ControleDTO;
import utfpr.farmdexp.estufa.services.ControleService;

@RestController
@RequestMapping("/controle")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Controles", description = "Endpoints para gerenciar controles")
public class ControleController {

    private final ControleService service;

    public ControleController(ControleService service) {
        this.service = service;
    }


    @Operation(summary = "Criar controle para estufa", description = "Cria um novo controle associado a uma estufa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ControleDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Estufa não encontrada"),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping("/estufa/{estufaId}")
    public ControleDTO criar(@PathVariable String estufaId, @Valid @RequestBody ControleDTO dto) {
        return service.salvar(estufaId, dto);
    }

    @Operation(summary = "Listar controles por estufa", description = "Lista os controles associados a uma estufa. Tem suporte à paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ControleDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Estufa não encontrada"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/estufa/{estufaId}")
    public Page<ControleDTO> listarPorEstufa(
            @PathVariable String estufaId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorEstufa(estufaId, pagina, tamanho);
    }

    @Operation(summary = "Listar controles", description = "Lista controles com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ControleDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<ControleDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @Operation(summary = "Obter controle por ID", description = "Retorna um controle específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ControleDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Controle não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public ControleDTO obterPorId(@PathVariable String id) {
        return ControleDTO.fromEntity(service.buscarPorId(id));
    }

    @Operation(summary = "Atualizar controle", description = "Atualiza os dados de um controle existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ControleDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Controle não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public ControleDTO atualizar(@PathVariable String id, @Valid @RequestBody ControleDTO dto) {
        return ControleDTO.fromEntity(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar controle", description = "Remove um controle existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Controle não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}
