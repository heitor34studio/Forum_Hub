package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.infra.model.Resposta.Resposta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    boolean existsByMensagem(String mensagem);

    boolean existsBySolucao(String solucao);

    Page<Resposta> findAllByTopicoId(Long id, Pageable paginacao);
}
