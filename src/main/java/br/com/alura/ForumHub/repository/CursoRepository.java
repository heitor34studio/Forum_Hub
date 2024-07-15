package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.infra.model.Curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByNome(String nome);
}
