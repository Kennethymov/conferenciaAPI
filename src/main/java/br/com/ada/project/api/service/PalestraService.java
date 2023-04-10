package br.com.ada.project.api.service;

import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Palestrante;
import br.com.ada.project.api.domain.Participante;
import br.com.ada.project.api.dto.PalestraDTO;
import br.com.ada.project.api.dto.ParticipanteDTO;
import br.com.ada.project.api.repository.PalestraRepository;
import br.com.ada.project.api.repository.PalestranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalestraService {
    private final PalestraRepository repository;
    private final PalestranteRepository palestranteRepository;

    public PalestraService(PalestraRepository repository, PalestranteRepository palestranteRepository) {
        this.repository = repository;
        this.palestranteRepository = palestranteRepository;
    }

    public Optional<Palestra> buscarPalestra(Long id) {
        return repository.findById(id);
    }

    public List<Palestra> listarPalestras() {
        return repository.findAll();
    }

    public Palestra cadastrarPalestra(PalestraDTO palestraDTO) {
        Optional<Palestrante> palestranteOptional = palestranteRepository.findById(palestraDTO.getPalestranteId());
        if (palestranteOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestrante não encontrado");
        }

        Palestrante palestrante = palestranteOptional.get();
        if (palestrante.getPalestra() != null) {
            throw new IllegalArgumentException("Palestrante já possui uma palestra cadastrada");
        }

        Palestra palestra = Palestra.builder()
                .nome(palestraDTO.getNome())
                .data(palestraDTO.getData())
                .palestrante(palestrante)
                .build();
        return repository.save(palestra);
    }

    public Palestra atualizarPalestra(Long id, PalestraDTO palestraDTO) {
        Optional<Palestra> palestraOptional = repository.findById(id);
        if (palestraOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestra não encontrada");
        }

        Optional<Palestrante> palestranteOptional = palestranteRepository.findById(palestraDTO.getPalestranteId());
        if (palestranteOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestrante não encontrado");
        }

        Palestrante palestrante = palestranteOptional.get();
        if (palestrante.getPalestra() != null && palestrante.getPalestra().getId() != id) {
            throw new IllegalArgumentException("Palestrante já possui uma palestra diferente cadastrada");
        }

        Palestra palestra = palestraOptional.get();
        palestra.setNome(palestraDTO.getNome());
        palestra.setData(palestraDTO.getData());
        palestra.setPalestrante(palestrante);

        palestrante.setPalestra(palestra);
        palestranteRepository.save(palestrante);
        return repository.save(palestra);
    }

    public void deletePalestra(Long id) {
        Optional<Palestra> palestraOptional = repository.findById(id);
        if (palestraOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestra não encontrada");
        }

        repository.deleteById(id);
    }

    public List<Participante> listarParticipantes(Long idPalestra) {
        Optional<Palestra> palestraOptional = repository.findById(idPalestra);
        if (palestraOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestra não encontrada");
        }

        Palestra palestra = palestraOptional.get();
        return palestra.getParticipantes();
    }

    public Participante inscreverParticipante(Long idPalestra, ParticipanteDTO participanteDTO) {
        Optional<Palestra> palestraOptional = repository.findById(idPalestra);
        if (palestraOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestra não encontrada");
        }

        Palestra palestra = palestraOptional.get();
        if (palestra.getParticipantes().size() >= 20) {
            throw new IllegalArgumentException("Palestra com limite de participantes atingido");
        }

        Participante participante = Participante.builder()
                .nome(participanteDTO.getNome())
                .email(participanteDTO.getEmail())
                .senha(participanteDTO.getSenha())
                .build();
        participante.setPalestra(palestra);
        palestra.getParticipantes().add(participante);
        repository.save(palestra);
        return participante;
    }
}
