package br.com.ada.project.api.dto;

import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Participante;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ParticipanteDTO {

    private  Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    public ParticipanteDTO(Participante participante) {
        this.id = participante.getId();
        this.nome = participante.getNome();
        this.email = participante.getEmail();
        this.senha = participante.getSenha();
    }

    public ParticipanteDTO() {
    }
}
