package utfpr.farmdexp.estufa.repositories;

import utfpr.farmdexp.estufa.models.Estufa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstufaRepository extends JpaRepository<Estufa, UUID> {
}
