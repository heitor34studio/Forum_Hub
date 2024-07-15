package br.com.alura.ForumHub.infra.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUser(@NotBlank String name,
                                @NotBlank @Email String email,
                                @NotBlank String senha,
                                @NotBlank String senhaIgual) {
}
