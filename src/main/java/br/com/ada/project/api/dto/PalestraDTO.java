package br.com.ada.project.api.dto;

import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Palestrante;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PalestraDTO {

    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    private LocalDate data;

    @NotBlank
    private Long palestranteId;

    public PalestraDTO(Palestra palestra) {
        this.id = palestra.getId();
        this.nome = palestra.getNome();
        this.data = palestra.getData();
        this.palestranteId = palestra.getPalestrante().getId();
    }

    public PalestraDTO() {
    }
}
