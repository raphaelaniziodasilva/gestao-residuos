package br.com.fiap.gestao_residuos.dto;

import jakarta.validation.constraints.*;

public record LixoDTO(
        Long id,

        @NotNull(message = "A capacidade é obrigatória!")
        @Min(value = 0, message = "A capacidade deve ser maior ou igual a zero!")
        Double capacidade,

        @NotBlank(message = "A localização é obrigatória!")
        @Size(max = 300, message = "A localização deve conter no máximo 300 caracteres!")
        String localizacao,

        @NotBlank(message = "O tipo é obrigatório!")
        @Size(max = 100, message = "O tipo deve conter no máximo 100 caracteres!")
        String tipo,

        @Size(max = 500, message = "A descrição deve conter no máximo 500 caracteres!")
        String descricao
) {
}
