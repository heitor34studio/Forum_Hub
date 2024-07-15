package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.infra.model.Topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTitulo(String titulo);

    boolean existsByMensagem(String mensagem);
}
