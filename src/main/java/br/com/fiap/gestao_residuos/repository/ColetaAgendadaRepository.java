package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.ColetaAgendada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ColetaAgendadaRepository extends JpaRepository<ColetaAgendada, Long> {
    List<ColetaAgendada> findByDataColeta(LocalDate dataColeta);
    List<ColetaAgendada> findByStatus(String status);
    long countByContatoId(Long contatoId);
}

