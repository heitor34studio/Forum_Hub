package br.com.alura.ForumHub.infra.dto.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroResposta(
        @NotBlank String mensagem,
        @NotBlank String solucao,
        @NotNull Long idUsuario,
        @NotNull Long idTopico
) {
}
