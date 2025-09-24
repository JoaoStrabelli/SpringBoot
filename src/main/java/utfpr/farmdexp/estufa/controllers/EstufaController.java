package utfpr.farmdexp.estufa.controllers;

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
public class EstufaController {

    private final EstufaService service;

    public EstufaController(EstufaService service) {
        this.service = service;
    }

    @PostMapping("/ambiente/{ambienteId}")
    public EstufaDTO criar(@PathVariable String ambienteId, @Valid @RequestBody EstufaDTO dto) {
        return service.salvar(ambienteId, dto);
    }

    @GetMapping("/ambiente/{ambienteId}")
    public Page<EstufaDTO> listarPorAmbiente(
            @PathVariable String ambienteId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorAmbiente(ambienteId, pagina, tamanho);
    }

    @GetMapping
    public Page<EstufaDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @GetMapping("/{id}")
    public EstufaDTO obterPorId(@PathVariable String id) {
        return EstufaDTO.fromEntity(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public EstufaDTO atualizar(@PathVariable String id, @Valid @RequestBody EstufaDTO dto) {
        return EstufaDTO.fromEntity(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}