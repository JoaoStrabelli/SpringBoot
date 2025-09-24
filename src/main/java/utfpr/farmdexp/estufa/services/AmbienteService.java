package utfpr.farmdexp.estufa.services;
import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import utfpr.farmdexp.estufa.dtos.AmbienteDTO;
import utfpr.farmdexp.estufa.models.Ambiente;
import utfpr.farmdexp.estufa.repositories.AmbienteRepository;

@Service
public class AmbienteService {

    Logger logger = Logger.getLogger(AmbienteService.class.getName());

    private final AmbienteRepository ambienteRepository;

    public AmbienteService(AmbienteRepository ambienteRepository) {
        this.ambienteRepository = ambienteRepository;
    }

    public AmbienteDTO salvar(AmbienteDTO ambienteDTO) {
        Ambiente ambiente = new Ambiente();
        BeanUtils.copyProperties(ambienteDTO, ambiente, "id");

        Ambiente salvo = ambienteRepository.save(ambiente);
        return AmbienteDTO.fromEntity(salvo);
    }

    public Page<AmbienteDTO> listarTodos(int pagina, int tamanho) {
        return ambienteRepository.findAll(PageRequest.of(pagina, tamanho)).map(AmbienteDTO::fromEntity);
    }

    public Ambiente buscarPorId(UUID id) {
        return ambienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ambiente não encontrado com ID: " + id));
    }

    public Ambiente buscarPorId(String id) {
        return buscarPorId(UUID.fromString(id));
    }

    public void deletar(String id) {
        logger.info("Deletando ambiente com ID: " + id);

        ambienteRepository.delete(buscarPorId(id));
    }

    public Ambiente atualizar(String id, AmbienteDTO dto) {
        var ambienteExistente = buscarPorId(id);
        BeanUtils.copyProperties(dto, ambienteExistente, "id");

        logger.info("Atualizando Ambiente: " + ambienteExistente);

        return ambienteRepository.save(ambienteExistente);
    }

}