package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Contato;

public record ContatoExibicaoDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String rua,
        String cidade,
        String estado,
        String cep
        ) {

        public ContatoExibicaoDTO(Contato contato) {
                this(
                        contato.getId(),
                        contato.getNome(),
                        contato.getEmail(),
                        contato.getTelefone(),
                        contato.getRua(),
                        contato.getCidade(),
                        contato.getEstado(),
                        contato.getCep()
                );
        }
}
