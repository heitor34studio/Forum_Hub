package br.com.alura.ForumHub.infra.dto.topico;

import br.com.alura.ForumHub.infra.model.Topico.Status;
import br.com.alura.ForumHub.infra.model.Topico.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(Long id, String titulo, String mensagem, Status status, LocalDateTime data) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getStatus(), topico.getData());
    }
}
