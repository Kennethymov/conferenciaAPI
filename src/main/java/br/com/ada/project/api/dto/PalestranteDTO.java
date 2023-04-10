package br.com.ada.project.api.dto;

import br.com.ada.project.api.domain.Palestra;
import br.com.ada.project.api.domain.Palestrante;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PalestranteDTO {

    private Long id;

    @NotBlank
    private String nome;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String cpf;

    @NotBlank
    private String senha;

    public PalestranteDTO(Palestrante palestrante) {
        this.id = palestrante.getId();
        this.nome = palestrante.getNome();
        this.email = palestrante.getEmail();
        this.cpf = palestrante.getCpf();
        this.senha = palestrante.getSenha();
    }

    public PalestranteDTO() {
    }
}
