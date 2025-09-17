package utfpr.farmdexp.estufa.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import utfpr.farmdexp.estufa.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {

    Page<Sensor> findByControleId(UUID controleId, Pageable pageable);
}
