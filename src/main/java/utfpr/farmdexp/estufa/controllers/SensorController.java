package utfpr.farmdexp.estufa.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import utfpr.farmdexp.estufa.dtos.SensorDTO;
import utfpr.farmdexp.estufa.services.SensorService;

@RestController
@RequestMapping("/sensor")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @PostMapping("/controle/{controleId}")
    public SensorDTO criar(@PathVariable String controleId, @Valid @RequestBody SensorDTO dto) {
        return service.salvar(controleId, dto);
    }

    @GetMapping("/controle/{controleId}")
    public Page<SensorDTO> listarPorAmbiente(
            @PathVariable String controleId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorControle(controleId, pagina, tamanho);
    }

    @GetMapping
    public Page<SensorDTO> listar(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarTodos(pagina, tamanho);
    }

    @GetMapping("/{id}")
    public SensorDTO obterPorId(@PathVariable String id) {
        return SensorDTO.fromEntity(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public SensorDTO atualizar(@PathVariable String id, @Valid @RequestBody SensorDTO dto) {
        return SensorDTO.fromEntity(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }
}