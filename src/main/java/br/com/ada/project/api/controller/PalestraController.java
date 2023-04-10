package br.com.ada.project.api.controller;

import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Palestrante;
import br.com.ada.project.api.domain.Participante;
import br.com.ada.project.api.dto.PalestraDTO;
import br.com.ada.project.api.dto.PalestranteDTO;
import br.com.ada.project.api.dto.ParticipanteDTO;
import br.com.ada.project.api.repository.PalestranteRepository;
import br.com.ada.project.api.service.PalestraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/palestras")
public class PalestraController {
    private final PalestraService service;

    public PalestraController(PalestraService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalestraDTO> buscarPalestra(@PathVariable Long id) {
        Optional<Palestra> optionalPalestra = service.buscarPalestra(id);
        if (optionalPalestra.isPresent()) {
            Palestra palestra = optionalPalestra.get();
            PalestraDTO palestraResponse = new PalestraDTO(palestra);
            return ResponseEntity.ok(palestraResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PalestraDTO>> listarPalestras() {
        List<Palestra> palestras = service.listarPalestras();
        List<PalestraDTO> palestrasResponse = palestras.stream().map(PalestraDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(palestrasResponse);
    }

    @PostMapping
    public ResponseEntity<PalestraDTO> cadastrarPalestra(@RequestBody PalestraDTO palestraDTO) {
        Palestra palestra = service.cadastrarPalestra(palestraDTO);
        PalestraDTO palestraResponse = new PalestraDTO(palestra);
        return ResponseEntity.ok(palestraResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PalestraDTO> atualizarPalestra(@PathVariable Long id, @RequestBody PalestraDTO palestraDTO) {
        Palestra palestra = service.atualizarPalestra(id, palestraDTO);
        PalestraDTO palestraResponse = new PalestraDTO(palestra);
        return ResponseEntity.ok(palestraResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePalestra(@PathVariable Long id) {
        service.deletePalestra(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/participantes")
    public ResponseEntity<List<ParticipanteDTO>> listarParticipantes(@PathVariable Long id) {
        Optional<Palestra> optionalPalestra = service.buscarPalestra(id);
        if (optionalPalestra.isPresent()) {
            List<Participante> participantes = service.listarParticipantes(id);
            List<ParticipanteDTO> participantesResponse = participantes.stream().map(ParticipanteDTO::new).collect(Collectors.toList());
            return ResponseEntity.ok(participantesResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/inscrever")
    public ResponseEntity<ParticipanteDTO> inscreverParticipante(@PathVariable Long id, @RequestBody ParticipanteDTO participanteRequest) {
        Optional<Palestra> optionalPalestra = service.buscarPalestra(id);
        if (optionalPalestra.isPresent()) {
            Participante participante = service.inscreverParticipante(id, participanteRequest);
            ParticipanteDTO participanteResponse = new ParticipanteDTO(participante);
            return ResponseEntity.ok(participanteResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
