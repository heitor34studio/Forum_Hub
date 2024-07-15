package br.com.alura.ForumHub.infra.dto.curso;

import br.com.alura.ForumHub.infra.model.Curso.CategoriasCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosAtualizaCurso(@NotNull Long id,
                                 String nome,
                                 String categoria) {
}
