package utfpr.farmdexp.estufa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import utfpr.farmdexp.estufa.models.Estufa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstufaRepository extends JpaRepository<Estufa, UUID> {

    Page<Estufa> findByAmbienteId(UUID ambienteId, Pageable pageable);
}
