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

import utfpr.farmdexp.estufa.models.Sensor;
import utfpr.farmdexp.estufa.repositories.SensorRepository;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping(value = {"", "/"})
    public List<Sensor> getSensor() {
        return sensorRepository.findAll();
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<Sensor> getById(
            @PathVariable String sensorId) {

        var uuid = UUID.fromString(sensorId);
        var result = sensorRepository.findById(uuid);

        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sensor create(@RequestBody Sensor entity) {
        System.out.println(entity);
        sensorRepository.save(entity);
        return entity;
    }
}
