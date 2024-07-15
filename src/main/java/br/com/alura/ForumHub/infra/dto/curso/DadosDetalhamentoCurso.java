package br.com.alura.ForumHub.infra.dto.curso;

import br.com.alura.ForumHub.infra.model.Curso.CategoriasCurso;
import br.com.alura.ForumHub.infra.model.Curso.Curso;

import java.util.List;

public record DadosDetalhamentoCurso(Long id, String nome, String categoria) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
