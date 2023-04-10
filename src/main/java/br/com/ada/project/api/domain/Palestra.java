package br.com.ada.project.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Palestra {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate data;

    // Relacionamento com a entidade Palestrante
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "palestrante_id")
    private Palestrante palestrante;

    // Relacionamento com a entidade Participante
    @OneToMany(mappedBy = "palestra")
    private List<Participante> participantes = new ArrayList<>();
}

