package br.com.ada.project.api.repository;

import br.com.ada.project.api.domain.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Optional<Participante> findByEmail(String email);

    List<Participante> findByPalestraId(Long palestraId);
}
