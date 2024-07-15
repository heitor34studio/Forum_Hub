package br.com.alura.ForumHub.infra.dto.resposta;

import br.com.alura.ForumHub.infra.model.Resposta.Resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(
        Long id, String mensagem, String solucao, LocalDateTime data
) {
    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getSolucao(), resposta.getMensagem(), resposta.getData());
    }
}
