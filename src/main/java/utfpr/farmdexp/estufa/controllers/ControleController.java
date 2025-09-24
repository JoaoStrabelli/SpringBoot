package utfpr.farmdexp.estufa.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import utfpr.farmdexp.estufa.dtos.ControleDTO;
import utfpr.farmdexp.estufa.services.ControleService;

@RestController
@RequestMapping("/controle")
public class ControleController {

    private final ControleService service;

    public ControleController(ControleService service) {
        this.service = service;
    }

    @PostMapping("/estufa/{estufaId}")
    public ControleDTO criar(@PathVariable String estufaId, @Valid @RequestBody ControleDTO dto) {
        return service.salvar(estufaId, dto);
    }

    @GetMapping("/estufa/{estufaId}")
    public Page<ControleDTO> listarPorEstufa(
            @PathVariable String estufaId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorEstufa(estufaId, pagina, tamanho);
    }

    @GetMapping
    public Page<ControleDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @GetMapping("/{id}")
    public ControleDTO obterPorId(@PathVariable String id) {
        return ControleDTO.fromEntity(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ControleDTO atualizar(@PathVariable String id, @Valid @RequestBody ControleDTO dto) {
        return ControleDTO.fromEntity(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}
