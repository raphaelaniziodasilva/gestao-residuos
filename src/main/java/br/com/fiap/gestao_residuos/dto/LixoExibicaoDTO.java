package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Lixo;

public record LixoExibicaoDTO(
        Long id,
        Double capacidade,
        String localizacao,
        String tipo,
        String descricao
) {
    public LixoExibicaoDTO(Lixo lixo) {
        this(
                lixo.getId(),
                lixo.getCapacidade(),
                lixo.getLocalizacao(),
                lixo.getTipo(),
                lixo.getDescricao()
        );
    }
}
