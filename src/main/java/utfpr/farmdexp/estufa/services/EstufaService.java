package utfpr.farmdexp.estufa.services;

import java.util.UUID;
import java.util.logging.Logger;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import utfpr.farmdexp.estufa.dtos.EstufaDTO;
import utfpr.farmdexp.estufa.models.Estufa;
import utfpr.farmdexp.estufa.repositories.AmbienteRepository;
import utfpr.farmdexp.estufa.repositories.EstufaRepository;

@Service
public class EstufaService {

    Logger logger = Logger.getLogger(EstufaService.class.getName());

    private final EstufaRepository estufaRepository;
    private final AmbienteRepository ambienteRepository;

    public EstufaService(EstufaRepository estufaRepository, AmbienteRepository ambienteRepository) {
        this.estufaRepository = estufaRepository;
        this.ambienteRepository = ambienteRepository;
    }

    public EstufaDTO salvar(String estufaId, EstufaDTO estufaDTO) {
        var ambiente = ambienteRepository.findById(UUID.fromString(estufaId))
                .orElseThrow(() -> new IllegalStateException("Estufa " + estufaId + " não encontrada."));

        Estufa estufa = new Estufa();
        BeanUtils.copyProperties(estufaDTO, estufa, "id");
        estufa.setAmbiente(ambiente);

        Estufa salvo = estufaRepository.save(estufa);
        return new EstufaDTO(salvo.getId(), salvo.getNome());
    }

    public Page<Estufa> listarTodos(int pagina, int tamanho) {
        return estufaRepository.findAll(PageRequest.of(pagina, tamanho));
    }

    public Estufa buscarPorId(UUID id) {
        return estufaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estufa não encontrado com ID: " + id));
    }

    public Estufa buscarPorId(String id) {
        return buscarPorId(UUID.fromString(id));
    }

    public void deletar(String id) {
        logger.info("Deletando Estufa com ID: " + id);

        estufaRepository.delete(buscarPorId(id));
    }

    public Estufa atualizar(String id, EstufaDTO dto) {
        var EstufaExistente = buscarPorId(id);
        BeanUtils.copyProperties(dto, EstufaExistente, "id");

        logger.info("Atualizando Estufa: " + EstufaExistente);

        return estufaRepository.save(EstufaExistente);
    }

    public Page<EstufaDTO> listarPorAmbiente(String estufaId, int pagina, int tamanho) {
        return estufaRepository.findByAmbienteId(UUID.fromString(estufaId), PageRequest.of(pagina, tamanho))
                .map(l -> new EstufaDTO(l.getId(), l.getNome()));
    }

}