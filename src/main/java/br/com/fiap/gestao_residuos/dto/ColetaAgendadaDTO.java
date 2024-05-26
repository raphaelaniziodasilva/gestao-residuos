package br.com.fiap.gestao_residuos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ColetaAgendadaDTO(
        Long id,

        @NotNull(message = "A data da coleta é obrigatória!")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dataColeta,

        @NotBlank(message = "O status é obrigatório!")
        String status,

        @Size(max = 500, message = "As observações devem conter no máximo 500 caracteres!")
        String observacoes,

        @NotNull(message = "O ID do contato é obrigatório!")
        Long contatoId,
        ContatoDTO contato,

        @NotNull(message = "O ID do lixo é obrigatório!")
        Long lixoId,
        LixoDTO lixo
) {
}
