package br.com.alura.ForumHub.infra.dto.curso;

import br.com.alura.ForumHub.infra.model.Curso.Curso;

public record DadosListagemCursos(Long id, String nome, String categorias) {

    public DadosListagemCursos(Curso curso){
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
