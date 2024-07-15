package br.com.alura.ForumHub.infra.model.Curso;

import br.com.alura.ForumHub.infra.dto.curso.DadosAtualizaCurso;
import br.com.alura.ForumHub.infra.dto.curso.DadosCadastroCurso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="cursos")
@Entity(name="Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;

    public Curso(DadosCadastroCurso dados) {
        this.nome = dados.nome();
        this.categoria = dados.categoria();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categorias) {
        this.categoria = categorias;
    }

    public void atualizarInformacoes(DadosAtualizaCurso dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.categoria() != null) {
            this.categoria = dados.categoria();
        }
    }
}
