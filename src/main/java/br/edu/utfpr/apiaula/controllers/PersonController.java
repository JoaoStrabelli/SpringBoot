package br.edu.utfpr.apiaula.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.apiaula.models.Person;
import br.edu.utfpr.apiaula.repositories.PersonRepository;



@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = {"", "/"})
    public List<Person> getPerson() {
        return personRepository.findAll();
    }
    
    @GetMapping("/{personId}")
    public ResponseEntity<Person> getById(
        @PathVariable String personId) {

        var uuid = UUID.fromString(personId);
        var result = personRepository.findById(uuid);

        return result.isPresent()
            ? ResponseEntity.ok(result.get())
            : ResponseEntity.notFound().build();
    }
    

    @PostMapping
    public Person create(@RequestBody Person entity) {
        System.out.println(entity);
        personRepository.save(entity);
        return entity;
    }
    

}
