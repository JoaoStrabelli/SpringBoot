package utfpr.farmdexp.estufa.controllers;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utfpr.farmdexp.estufa.models.Ambiente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;
import utfpr.farmdexp.estufa.services.AmbienteService;
import utfpr.farmdexp.estufa.repositories.AmbienteRepository;
import utfpr.farmdexp.estufa.dtos.AmbienteDTO;

@RestController
@RequestMapping("/ambientes")
public class AmbienteController {
    @Autowired
    private AmbienteService ambienteService;

    @GetMapping
    public Page<Ambiente> list(@PageableDefault(size = 20) Pageable pageable) {
        return ambienteService.findAll(pageable);
    }

    @GetMapping("/{id}")
    public Ambiente getById(@PathVariable UUID id) {
        return ambienteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Ambiente> create(@Valid @RequestBody AmbienteDTO dto, UriComponentsBuilder uriBuilder) {
        Ambiente saved = ambienteService.create(dto);
        URI location = uriBuilder.path("/ambientes/{id}").buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public Ambiente update(@PathVariable UUID id, @Valid @RequestBody AmbienteDTO dto) {
        return ambienteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        ambienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}