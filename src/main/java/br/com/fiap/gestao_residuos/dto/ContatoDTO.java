package br.com.fiap.gestao_residuos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ContatoDTO(
        Long id,

        @NotBlank(message = "O nome do contato é obrigatório!")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório!")
        @Email(message = "O e-mail não é válido!")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Size(min = 10, max = 15, message = "O telefone deve conter entre 10 e 15 caracteres!")
        String telefone,

        @NotBlank(message = "A rua é obrigatória")
        String rua,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "O estado é obrigatório")
        String estado,

        @NotBlank(message = "O CEP é obrigatório")
        @Size(min = 8, max = 9, message = "O CEP deve conter 8 ou 9 caracteres!")
        String cep
) {
}
