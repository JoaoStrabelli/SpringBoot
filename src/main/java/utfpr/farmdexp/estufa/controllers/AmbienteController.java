package utfpr.farmdexp.estufa.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import utfpr.farmdexp.estufa.services.AmbienteService;
import utfpr.farmdexp.estufa.dtos.AmbienteDTO;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {

    private final AmbienteService service;

    public AmbienteController(AmbienteService service) {
        this.service = service;
    }

    @PostMapping
    public AmbienteDTO criar(@Valid @RequestBody AmbienteDTO dto) {
        return service.salvar(dto);
    }

    @GetMapping
    public Page<AmbienteDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @GetMapping("/{id}")
    public AmbienteDTO obterPorId(@PathVariable String id) {
        return AmbienteDTO.fromEntity(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public AmbienteDTO atualizar(@PathVariable String id, @Valid @RequestBody AmbienteDTO dto) {
        return AmbienteDTO.fromEntity(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

}