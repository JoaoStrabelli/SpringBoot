package utfpr.farmdexp.estufa.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import utfpr.farmdexp.estufa.dtos.SensorDTO;
import utfpr.farmdexp.estufa.models.Sensor;
import utfpr.farmdexp.estufa.services.SensorService;

@RestController
@RequestMapping("/controle/{controleId}/sensores")
public class SensorController {

    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @PostMapping
    public SensorDTO criar(@PathVariable String controleId, @Valid @RequestBody SensorDTO dto) {
        return service.salvar(controleId, dto);
    }

    @GetMapping
    public Page<SensorDTO> listarPorControle(
            @PathVariable String controleId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorControle(controleId, pagina, tamanho);
    }


//    @GetMapping
//    public Page<Sensor> listar(
//            @RequestParam(defaultValue = "0") int pagina,
//            @RequestParam(defaultValue = "10") int tamanho) {
//        return service.listarTodos(pagina, tamanho);
//    }

    @GetMapping("/{id}")
    public Sensor obterPorId(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Sensor atualizar(@PathVariable String id, @Valid @RequestBody SensorDTO dto) {
        return service.atualizar(id, dto);
    }
}
