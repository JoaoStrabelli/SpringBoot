package utfpr.farmdexp.estufa.repositories;

import utfpr.farmdexp.estufa.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
}
