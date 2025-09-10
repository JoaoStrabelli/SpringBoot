package utfpr.farmdexp.estufa.repositories;

import utfpr.farmdexp.estufa.models.Ambiente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AmbienteRepository extends JpaRepository<Ambiente, UUID> {
}
