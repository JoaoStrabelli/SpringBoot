package utfpr.farmdexp.estufa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import utfpr.farmdexp.estufa.models.Controle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ControleRepository extends JpaRepository<Controle, UUID> {

    Page<Controle> findByEstufaId(UUID estufaId, Pageable pageable);
}
