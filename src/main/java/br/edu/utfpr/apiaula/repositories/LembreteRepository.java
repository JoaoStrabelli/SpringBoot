package br.edu.utfpr.apiaula.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.apiaula.models.Lembrete;

public interface LembreteRepository extends JpaRepository<Lembrete, UUID> {

    Page<Lembrete> findByPersonId(UUID pessoaId, Pageable pageable);
}
