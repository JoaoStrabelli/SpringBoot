package utfpr.farmdexp.estufa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utfpr.farmdexp.estufa.models.Estufa;
import utfpr.farmdexp.estufa.repositories.EstufaRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estufa")
public class EstufaController {
    @Autowired
    private EstufaRepository estufaRepository;

    @GetMapping(value = {"", "/"})
    public List<Estufa> getEstufa() {
        return estufaRepository.findAll();
    }

    @GetMapping("/{estufaId}")
    public ResponseEntity<Estufa> getById(
            @PathVariable String estufaId) {

        var uuid = UUID.fromString(estufaId);
        var result = estufaRepository.findById(uuid);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Estufa create(@RequestBody Estufa entity) {
        System.out.println(entity);
        estufaRepository.save(entity);
        return entity;
    }
}