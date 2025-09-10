package utfpr.farmdexp.estufa.repositories;

import utfpr.farmdexp.estufa.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}
