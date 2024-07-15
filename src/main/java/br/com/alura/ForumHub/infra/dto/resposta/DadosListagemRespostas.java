package br.com.alura.ForumHub.infra.dto.resposta;

import br.com.alura.ForumHub.infra.model.Resposta.Resposta;

import java.time.LocalDateTime;

public record DadosListagemRespostas(
        Long id,
        String mensagem,
        String solucao,
        LocalDateTime data,
        String usuario) {
    public DadosListagemRespostas(Resposta resposta) {
        this(
                resposta.getId(),
                resposta.getMensagem(),
                resposta.getSolucao(),
                resposta.getData(),
                resposta.getUsuario().getNome()
        );
    }
}
