package br.com.ada.project.api.controller;


import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Palestrante;
import br.com.ada.project.api.dto.PalestraDTO;
import br.com.ada.project.api.dto.PalestranteDTO;
import br.com.ada.project.api.repository.PalestranteRepository;
import br.com.ada.project.api.service.PalestraService;
import br.com.ada.project.api.service.PalestranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/palestrantes")
public class PalestranteController {
    private PalestranteService service;

    public PalestranteController(PalestranteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PalestranteDTO> buscarPalestrante(@PathVariable Long id) {
        Optional<Palestrante> optionalPalestrante = service.buscarPalestrante(id);
        if (optionalPalestrante.isPresent()) {
            Palestrante palestrante = optionalPalestrante.get();
            PalestranteDTO palestranteResponse = new PalestranteDTO(palestrante);
            return ResponseEntity.ok(palestranteResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PalestranteDTO>> listarPalestrantes() {
        List<Palestrante> palestrantes = service.listarPalestrantes();
        List<PalestranteDTO> palestrantesResponse = palestrantes.stream().map(PalestranteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(palestrantesResponse);
    }

    @PostMapping
    public ResponseEntity<PalestranteDTO> cadastrarPalestrante(@RequestBody PalestranteDTO palestranteDTO) {
        Palestrante palestrante = service.cadastrarPalestrante(palestranteDTO);
        PalestranteDTO palestranteResponse = new PalestranteDTO(palestrante);
        return ResponseEntity.ok(palestranteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PalestranteDTO> atualizarPalestrante(@PathVariable Long id, @RequestBody PalestranteDTO palestranteDTO) {
        Palestrante palestrante = service.atualizarPalestrante(id, palestranteDTO);
        PalestranteDTO palestranteResponse = new PalestranteDTO(palestrante);
        return ResponseEntity.ok(palestranteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePalestrante(@PathVariable Long id) {
        service.deletePalestrante(id);
        return ResponseEntity.noContent().build();
    }
}
