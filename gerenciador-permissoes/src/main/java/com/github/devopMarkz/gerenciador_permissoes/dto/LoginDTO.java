package com.github.devopMarkz.gerenciador_permissoes.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Login precisa ser preenchido.")
        String login,

        @NotBlank(message = "Senha precisa ser preenchida.")
        String senha
) {
}
