package utfpr.farmdexp.estufa.repositories;

import utfpr.farmdexp.estufa.models.Controle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ControleRepository extends JpaRepository<Controle, UUID> {
}
