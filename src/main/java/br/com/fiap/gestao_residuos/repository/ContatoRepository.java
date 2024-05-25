package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Optional<Contato> findByEmail(String email);
}
