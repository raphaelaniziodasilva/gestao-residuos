package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.ColetaAgendada;

public record ColetaAgendadaExibicaoDTO(
        Long id,
        String dataColeta,
        String status,
        String observacoes,
        ContatoDTO contato,
        LixoDTO lixo
) {
    public ColetaAgendadaExibicaoDTO(ColetaAgendada coletaAgendada) {
        this(
                coletaAgendada.getId(),
                coletaAgendada.getDataColeta().toString(),
                coletaAgendada.getStatus(),
                coletaAgendada.getObservacoes(),
                new ContatoDTO(
                        coletaAgendada.getContato().getId(),
                        coletaAgendada.getContato().getNome(),
                        coletaAgendada.getContato().getEmail(),
                        coletaAgendada.getContato().getTelefone(),
                        coletaAgendada.getContato().getTelefone(),
                        coletaAgendada.getContato().getRua(),
                        coletaAgendada.getContato().getCidade(),
                        coletaAgendada.getContato().getCep()

                ),
                new LixoDTO(
                        coletaAgendada.getLixo().getId(),
                        coletaAgendada.getLixo().getCapacidade(),
                        coletaAgendada.getLixo().getLocalizacao(),
                        coletaAgendada.getLixo().getTipo(),
                        coletaAgendada.getLixo().getDescricao()
                )
        );
    }
}
