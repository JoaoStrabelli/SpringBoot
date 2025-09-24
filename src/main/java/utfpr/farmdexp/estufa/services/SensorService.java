package utfpr.farmdexp.estufa.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utfpr.farmdexp.estufa.dtos.SensorDTO;
import utfpr.farmdexp.estufa.models.Sensor;
import utfpr.farmdexp.estufa.repositories.ControleRepository;
import utfpr.farmdexp.estufa.repositories.SensorRepository;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class SensorService {

    Logger logger = Logger.getLogger(SensorService.class.getName());

    private final SensorRepository sensorRepository;
    private final ControleRepository controleRepository;

    public SensorService(SensorRepository sensorRepository, ControleRepository controleRepository) {
        this.sensorRepository = sensorRepository;
        this.controleRepository = controleRepository;
    }

    public SensorDTO salvar(String controleId, SensorDTO sensorDTO) {
         var controle = controleRepository.findById(UUID.fromString(controleId))
                .orElseThrow(() -> new IllegalStateException("Controle " + controleId + " não encontrada."));

        Sensor sensor = new Sensor();
        BeanUtils.copyProperties(sensorDTO, sensor, "id");
        sensor.setControle(controle);

        Sensor salvo = sensorRepository.save(sensor);
        return new SensorDTO(salvo.getId(), salvo.getTipo());
    }

    public Page<SensorDTO> listarTodos(int pagina, int tamanho) {
        return sensorRepository.findAll(PageRequest.of(pagina, tamanho)).map(SensorDTO::fromEntity);
    }

    public Sensor buscarPorId(UUID id) {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sensor não encontrada com ID: " + id));
    }

    public Sensor buscarPorId(String id) {
        return buscarPorId(UUID.fromString(id));
    }

    public void deletar(String id) {
        logger.info("Deletando Sensor com ID: " + id);

        sensorRepository.delete(buscarPorId(id));
    }

    public Sensor atualizar(String id, SensorDTO SensorDTO) {
        var sensorExistente = buscarPorId(id);
        BeanUtils.copyProperties(SensorDTO, sensorExistente, "id");

        logger.info("Atualizando Sensor: " + sensorExistente);

        return sensorRepository.save(sensorExistente);
    }

    public Page<SensorDTO> listarPorControle(String controleId, int pagina, int tamanho) {
        return sensorRepository.findByControleId(UUID.fromString(controleId), PageRequest.of(pagina, tamanho))
                .map(l -> new SensorDTO(l.getId(), l.getTipo()));
    }

}