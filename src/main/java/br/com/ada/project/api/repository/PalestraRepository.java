package br.com.ada.project.api.repository;

import br.com.ada.project.api.domain.Palestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PalestraRepository extends JpaRepository<Palestra, Long> {
    Optional<Palestra> findByNome(String nome);

    Optional<Palestra> findByPalestranteId(Long id);
}
