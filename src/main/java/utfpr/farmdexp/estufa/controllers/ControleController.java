package utfpr.farmdexp.estufa.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utfpr.farmdexp.estufa.models.Controle;
import utfpr.farmdexp.estufa.repositories.ControleRepository;

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
