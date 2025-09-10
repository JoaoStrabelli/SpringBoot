package br.edu.utfpr.apiaula.services;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.apiaula.dtos.LembreteDTO;
import br.edu.utfpr.apiaula.models.Lembrete;
import br.edu.utfpr.apiaula.models.Person;
import br.edu.utfpr.apiaula.repositories.LembreteRepository;
import br.edu.utfpr.apiaula.repositories.PersonRepository;

@Service
public class LembreteService {
    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private LembreteRepository lembreteRepo;

    public Lembrete salvar(String pessoaId, LembreteDTO dto) {
        Person person = personRepo.findById(UUID.fromString(pessoaId))
                .orElseThrow(() -> 
                new RuntimeException("Pessoa " + pessoaId + " não existe"));
        
        var lembrete = new Lembrete();
        BeanUtils.copyProperties(dto, lembrete);
        lembrete.setPerson(person);

        return lembreteRepo.save(lembrete);
    }

}