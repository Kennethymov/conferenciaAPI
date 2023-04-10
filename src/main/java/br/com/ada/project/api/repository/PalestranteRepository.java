package br.com.ada.project.api.repository;

import br.com.ada.project.api.domain.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PalestranteRepository extends JpaRepository<Palestrante, Long> {
    Optional<Palestrante> findByEmail(String email);
    Optional<Palestrante> findByCpf(String cpf);
}
