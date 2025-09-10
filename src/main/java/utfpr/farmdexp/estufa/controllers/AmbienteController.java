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
import utfpr.farmdexp.estufa.models.Ambiente;
import utfpr.farmdexp.estufa.repositories.AmbienteRepository;

@RestController
@RequestMapping("/ambiente")
public class AmbienteController {
    @Autowired
    private AmbienteRepository ambienteRepository;

    @GetMapping(value = {"", "/"})
    public List<Ambiente> getAmbiente() {
        return ambienteRepository.findAll();
    }

    @GetMapping("/{ambienteId}")
    public ResponseEntity<Ambiente> getById(
            @PathVariable String ambienteId) {

        var uuid = UUID.fromString(ambienteId);
        var result = ambienteRepository.findById(uuid);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public Ambiente create(@RequestBody Ambiente entity) {
        System.out.println(entity);
        ambienteRepository.save(entity);
        return entity;
    }
}