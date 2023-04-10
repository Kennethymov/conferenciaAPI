package br.com.ada.project.api.service;

import br.com.ada.project.api.domain.Palestrante;
import br.com.ada.project.api.dto.PalestranteDTO;
import br.com.ada.project.api.repository.PalestranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalestranteService {
    private final PalestranteRepository repository;

    public PalestranteService(PalestranteRepository repository) {
        this.repository = repository;
    }

    public Optional<Palestrante> buscarPalestrante(Long id) {
        return repository.findById(id);
    }

    public List<Palestrante> listarPalestrantes() {
        return repository.findAll();
    }

    public Palestrante cadastrarPalestrante(PalestranteDTO palestranteDTO) {
        Optional<Palestrante> palestranteOptional = repository.findByEmail(palestranteDTO.getEmail());
        if (palestranteOptional.isPresent()) {
            throw new IllegalArgumentException("Já existe um palestrante com esse email cadastrado");
        }

        palestranteOptional = repository.findByCpf(palestranteDTO.getCpf());
        if (palestranteOptional.isPresent()) {
            throw new IllegalArgumentException("Já existe um palestrante com esse cpf cadastrado");
        }

        Palestrante palestrante = Palestrante.builder()
                .nome(palestranteDTO.getNome())
                .email(palestranteDTO.getEmail())
                .cpf(palestranteDTO.getCpf())
                .senha(palestranteDTO.getSenha())
                .build();
        return repository.save(palestrante);
    }

    public Palestrante atualizarPalestrante(Long id, PalestranteDTO palestranteDTO) {
        Optional<Palestrante> palestranteOptional = repository.findById(id);
        if (palestranteOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestrante não encontrado");
        }

        Palestrante palestrante = palestranteOptional.get();
        palestrante.setNome(palestranteDTO.getNome());
        palestrante.setEmail(palestranteDTO.getEmail());
        palestrante.setCpf(palestranteDTO.getCpf());
        palestrante.setSenha(palestranteDTO.getSenha());

        return repository.save(palestrante);
    }

    public void deletePalestrante(Long id) {
        Optional<Palestrante> palestranteOptional = repository.findById(id);
        if (palestranteOptional.isEmpty()) {
            throw new IllegalArgumentException("Palestrante não encontrado");
        }

        repository.deleteById(id);
    }
}

