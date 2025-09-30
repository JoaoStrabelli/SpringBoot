package utfpr.farmdexp.estufa.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import utfpr.farmdexp.estufa.services.AmbienteService;
import utfpr.farmdexp.estufa.dtos.AmbienteDTO;

@RestController
@RequestMapping("/ambiente")
@SecurityRequirement(name = "Authorization")
@Tag(name = "Ambientes", description = "Endpoints para gerenciar ambientes")
public class AmbienteController {

    private final AmbienteService service;

    public AmbienteController(AmbienteService service) {
        this.service = service;
    }

    @Operation(summary = "Criar Ambiente", description = "Cria um novo ambiente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AmbienteDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500")
    })
    @PostMapping
    public AmbienteDTO criar(@Valid @RequestBody AmbienteDTO dto) {
        return service.salvar(dto);
    }

    @Operation(summary = "Listar ambientes", description = "Lista ambientes com paginação.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AmbienteDTO.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping
    public Page<AmbienteDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @Operation(summary = "Obter ambiente por ID", description = "Retorna um ambiente específico pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AmbienteDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", description = "Ambiente não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @GetMapping("/{id}")
    public AmbienteDTO obterPorId(@PathVariable String id) {
        return AmbienteDTO.fromEntity(service.buscarPorId(id));
    }

    @Operation(summary = "Atualizar ambiente", description = "Atualiza os dados de um ambiente existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AmbienteDTO.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Ambiente não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @PutMapping("/{id}")
    public AmbienteDTO atualizar(@PathVariable String id, @Valid @RequestBody AmbienteDTO dto) {
        return AmbienteDTO.fromEntity(service.atualizar(id, dto));
    }

    @Operation(summary = "Deletar ambiente", description = "Remove um ambiente existente pelo seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ambiente não encontrado"),
            @ApiResponse(responseCode = "500")
    })
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

}