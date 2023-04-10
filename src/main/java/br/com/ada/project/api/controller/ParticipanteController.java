package br.com.ada.project.api.controller;

import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Participante;
import br.com.ada.project.api.domain.Participante;
import br.com.ada.project.api.dto.ParticipanteDTO;
import br.com.ada.project.api.repository.PalestraRepository;
import br.com.ada.project.api.repository.ParticipanteRepository;
import br.com.ada.project.api.service.PalestranteService;
import br.com.ada.project.api.service.ParticipanteService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    private ParticipanteService service;

    public ParticipanteController(ParticipanteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> buscarParticipante(@PathVariable Long id) {
        Optional<Participante> optionalParticipante = service.buscarParticipante(id);
        if (optionalParticipante.isPresent()) {
            Participante participante = optionalParticipante.get();
            ParticipanteDTO participanteResponse = new ParticipanteDTO(participante);
            return ResponseEntity.ok(participanteResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ParticipanteDTO>> listarParticipantes() {
        List<Participante> participantes = service.listarParticipantes();
        List<ParticipanteDTO> participantesResponse = participantes.stream().map(ParticipanteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(participantesResponse);
    }

    @PostMapping
    public ResponseEntity<ParticipanteDTO> cadastrarParticipante(@RequestBody ParticipanteDTO participanteDTO) {
        Participante participante = service.cadastrarParticipante(participanteDTO);
        ParticipanteDTO participanteResponse = new ParticipanteDTO(participante);
        return ResponseEntity.ok(participanteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> atualizarParticipante(@PathVariable Long id, @RequestBody ParticipanteDTO participanteDTO) {
        Participante participante = service.atualizarParticipante(id, participanteDTO);
        ParticipanteDTO participanteResponse = new ParticipanteDTO(participante);
        return ResponseEntity.ok(participanteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        service.deleteParticipante(id);
        return ResponseEntity.noContent().build();
    }
}
