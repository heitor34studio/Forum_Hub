package br.com.alura.ForumHub.infra.dto.resposta;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaResposta(@NotNull Long id,
                                    String mensagem,
                                    String solucao
                                    ) {
}
