package br.com.ada.project.api.service;

import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Palestrante;
import br.com.ada.project.api.domain.Participante;
import br.com.ada.project.api.dto.PalestraDTO;
import br.com.ada.project.api.dto.PalestranteDTO;
import br.com.ada.project.api.dto.ParticipanteDTO;
import br.com.ada.project.api.repository.PalestraRepository;
import br.com.ada.project.api.repository.PalestranteRepository;
import br.com.ada.project.api.repository.ParticipanteRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteService implements UserDetailsService {
    private final ParticipanteRepository repository;

    public ParticipanteService(ParticipanteRepository repository) {
        this.repository = repository;
    }

    public Optional<Participante> buscarParticipante(Long id) {
        return repository.findById(id);
    }

    public List<Participante> listarParticipantes() {
        return repository.findAll();
    }

    public Participante cadastrarParticipante(ParticipanteDTO participanteDTO) {
        Optional<Participante> participanteOptional = repository.findByEmail(participanteDTO.getEmail());
        if (participanteOptional.isPresent()) {
            throw new IllegalArgumentException("Já existe um participante com esse email cadastrado");
        }

        Participante participante = Participante.builder()
                .nome(participanteDTO.getNome())
                .email(participanteDTO.getEmail())
                .senha(participanteDTO.getSenha())
                .build();
        return repository.save(participante);
    }

    public Participante atualizarParticipante(Long id, ParticipanteDTO participanteDTO) {
        Optional<Participante> participanteOptional = repository.findById(id);
        if (participanteOptional.isEmpty()) {
            throw new IllegalArgumentException("Participante não encontrado");
        }

        Participante participante = participanteOptional.get();
        participante.setNome(participanteDTO.getNome());
        participante.setEmail(participanteDTO.getEmail());
        participante.setSenha(participanteDTO.getSenha());

        return repository.save(participante);
    }

    public void deleteParticipante(Long id) {
        Optional<Participante> participanteOptional = repository.findById(id);
        if (participanteOptional.isEmpty()) {
            throw new IllegalArgumentException("Participante não encontrado");
        }

        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
