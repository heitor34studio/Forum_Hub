package br.com.alura.ForumHub.infra.dto.topico;

import br.com.alura.ForumHub.infra.model.Topico.Status;
import br.com.alura.ForumHub.infra.model.Topico.Topico;

import java.time.LocalDateTime;

public record DadosListagemTopicos(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime data,
        Status status,
        String usuario,
        String curso
        ) {

        public DadosListagemTopicos(Topico topico) {
                this(
                        topico.getId(),
                        topico.getTitulo(),
                        topico.getMensagem(),
                        topico.getData(),
                        topico.getStatus(),
                        topico.getUsuario().getNome(),
                        topico.getCurso().getNome()
                );
        }
}
