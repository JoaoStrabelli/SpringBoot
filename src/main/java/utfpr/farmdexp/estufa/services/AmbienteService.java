package utfpr.farmdexp.estufa.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import utfpr.farmdexp.estufa.dtos.AmbienteDTO;
import utfpr.farmdexp.estufa.exception.NotFoundException;
import utfpr.farmdexp.estufa.models.Ambiente;
import org.springframework.beans.factory.annotation.Autowired;
import utfpr.farmdexp.estufa.repositories.AmbienteRepository;

import java.util.UUID;
// oi
@Service
public class AmbienteService {
    @Autowired
    private AmbienteRepository ambienteRepository;

    public Page<Ambiente> findAll(Pageable pageable) {
        return ambienteRepository.findAll(pageable);
    }

    public Ambiente findById(UUID id) {
        return ambienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ambiente não encontrado"));
    }

    public Ambiente create(AmbienteDTO dto) {
        Ambiente entity = new Ambiente();
        entity.setNome(dto.nome());
        return ambienteRepository.save(entity);
    }

    public Ambiente update(UUID id, AmbienteDTO dto) {
        Ambiente entity = findById(id);
        entity.setNome(dto.nome());
        return ambienteRepository.save(entity);
    }

    public void delete(UUID id) {
        Ambiente entity = findById(id);
        ambienteRepository.delete(entity);
    }
}