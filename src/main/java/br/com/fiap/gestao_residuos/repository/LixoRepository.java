package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.Lixo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LixoRepository extends JpaRepository<Lixo, Long> {
    List<Lixo> findByTipo(String tipo);
    List<Lixo> findByLocalizacao(String localizacao);
}
