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
import utfpr.farmdexp.estufa.models.Estufa;
import utfpr.farmdexp.estufa.services.EstufaService;

@RestController
@RequestMapping("/ambiente/{ambienteId}/estufas")
public class EstufaController {

    private EstufaService service;

    public EstufaController(EstufaService service) {
        this.service = service;
    }

    @PostMapping
    public EstufaDTO criar(@PathVariable String ambienteId, @Valid @RequestBody EstufaDTO dto) {
        return service.salvar(ambienteId, dto);
    }

    @GetMapping
    public Page<EstufaDTO> listarPorAmbiente(
            @PathVariable String ambienteId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorAmbiente(ambienteId, pagina, tamanho);
    }

//    @GetMapping
//    public Page<Sensor> listar(
//            @RequestParam(defaultValue = "0") int pagina,
//            @RequestParam(defaultValue = "10") int tamanho) {
//        return service.listarTodos(pagina, tamanho);
//    }

    @GetMapping("/{id}")
    public Estufa obterPorId(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Estufa atualizar(@PathVariable String id, @Valid @RequestBody EstufaDTO dto) {
        return service.atualizar(id, dto);
    }
}