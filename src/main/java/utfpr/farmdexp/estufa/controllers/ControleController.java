package utfpr.farmdexp.estufa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.farmdexp.estufa.models.Controle;
import utfpr.farmdexp.estufa.repositories.ControleRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/controle")
public class ControleController {
    @Autowired
    private ControleRepository controleRepository;

    @GetMapping(value = {"", "/"})
    public List<Controle> getAmbiente() {
        return controleRepository.findAll();
    }

    @GetMapping("/{controleId}")
    public ResponseEntity<Controle> getById(
            @PathVariable String controleId) {

        var uuid = UUID.fromString(controleId);
        var result = controleRepository.findById(uuid);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Controle create(@RequestBody Controle entity) {
        System.out.println(entity);
        controleRepository.save(entity);
        return entity;
    }
}