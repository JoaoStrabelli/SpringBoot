package utfpr.farmdexp.estufa.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import utfpr.farmdexp.estufa.dtos.ControleDTO;
import utfpr.farmdexp.estufa.models.Controle;
import utfpr.farmdexp.estufa.repositories.ControleRepository;
import utfpr.farmdexp.estufa.repositories.EstufaRepository;

import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ControleService {

    Logger logger = Logger.getLogger(ControleService.class.getName());

    private final ControleRepository controleRepository;
    private final EstufaRepository estufaRepository;

    public ControleService(ControleRepository sensorRepository, EstufaRepository controleRepository) {
        this.controleRepository = sensorRepository;
        this.estufaRepository = controleRepository;
    }

    public ControleDTO salvar(String estufaId, ControleDTO controleDTO) {
        var estufa = estufaRepository.findById(UUID.fromString(estufaId))
                .orElseThrow(() -> new IllegalStateException("Estufa " + estufaId + " não encontrada."));

        Controle controle = new Controle();
        BeanUtils.copyProperties(controleDTO, controle, "id");
        controle.setEstufa(estufa);

        Controle salvo = controleRepository.save(controle);
        return ControleDTO.fromEntity(salvo);
    }

    public Page<ControleDTO> listarTodos(int pagina, int tamanho) {
        return controleRepository.findAll(PageRequest.of(pagina, tamanho)).map(ControleDTO::fromEntity);
    }

    public Controle buscarPorId(UUID id) {
        return controleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Controle não encontrado com ID: " + id));
    }

    public Controle buscarPorId(String id) {
        return buscarPorId(UUID.fromString(id));
    }

    public void deletar(String id) {
        logger.info("Deletando Controle com ID: " + id);

        controleRepository.delete(buscarPorId(id));
    }

    public Controle atualizar(String id, ControleDTO dto) {
        var controleExistente = buscarPorId(id);
        BeanUtils.copyProperties(dto, controleExistente, "id");

        logger.info("Atualizando Controle: " + controleExistente);

        return controleRepository.save(controleExistente);
    }

    public Page<ControleDTO> listarPorEstufa(String estufaId, int pagina, int tamanho) {
        return controleRepository.findByEstufaId(UUID.fromString(estufaId), PageRequest.of(pagina, tamanho))
                .map(l -> new ControleDTO(l.getId(), l.getTipo()));
    }

}