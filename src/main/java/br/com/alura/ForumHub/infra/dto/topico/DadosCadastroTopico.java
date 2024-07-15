package br.com.alura.ForumHub.infra.dto.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotBlank String titulo,
        @NotBlank String mensagem,
        @NotNull Long idUsuario,
        @NotNull Long idCurso
    ) {
}
