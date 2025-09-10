package br.edu.utfpr.apiaula.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.apiaula.models.Person;

public interface PersonRepository 
                    extends JpaRepository<Person, UUID> {
    
    //Obter por email
    //public List<Person> findByEmail(String email);
}
