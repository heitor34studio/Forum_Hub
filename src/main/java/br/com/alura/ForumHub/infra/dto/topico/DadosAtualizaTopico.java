package br.com.alura.ForumHub.infra.dto.topico;

import br.com.alura.ForumHub.infra.model.Topico.Status;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaTopico(
        @NotNull Long id,
        String titulo,
        String mensagem,
        Status status
        ) {
}
