package utfpr.farmdexp.estufa.controllers;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import utfpr.farmdexp.estufa.dtos.ControleDTO;
import utfpr.farmdexp.estufa.models.Controle;
import utfpr.farmdexp.estufa.services.ControleService;

@RestController
@RequestMapping("/estufa/{estufaId}/controles")
public class ControleController {

    private final ControleService service;

    public ControleController(ControleService service) {
        this.service = service;
    }

    @PostMapping
    public ControleDTO criar(@PathVariable String estufaId, @Valid @RequestBody ControleDTO dto) {
        return service.salvar(estufaId, dto);
    }

    @GetMapping
    public Page<ControleDTO> listarPorEstufa(
            @PathVariable String estufaId,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanho) {
        return service.listarPorEstufa(estufaId, pagina, tamanho);
    }

//    @GetMapping
//    public Page<Sensor> listar(
//            @RequestParam(defaultValue = "0") int pagina,
//            @RequestParam(defaultValue = "10") int tamanho) {
//        return service.listarTodos(pagina, tamanho);
//    }

    @GetMapping("/{id}")
    public Controle obterPorId(@PathVariable String id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Controle atualizar(@PathVariable String id, @Valid @RequestBody ControleDTO dto) {
        return service.atualizar(id, dto);
    }
}
