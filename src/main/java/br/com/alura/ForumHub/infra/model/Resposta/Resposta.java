package br.com.alura.ForumHub.infra.model.Resposta;

import br.com.alura.ForumHub.infra.dto.resposta.DadosAtualizaResposta;
import br.com.alura.ForumHub.infra.dto.resposta.DadosCadastroResposta;
import br.com.alura.ForumHub.infra.model.Topico.Topico;
import br.com.alura.ForumHub.infra.model.User.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name="respostas")
@Entity(name="Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private String solucao;
    private LocalDateTime data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario usuario;

    public Resposta(DadosCadastroResposta dados, Usuario user, Topico topico) {
        this.mensagem = dados.mensagem();
        this.solucao = dados.solucao();
        this.data =  LocalDateTime.now();
        this.topico = topico;
        this.usuario = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void atualizarInformacoes(DadosAtualizaResposta dados) {

        if(dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }

        if(dados.solucao() != null) {
            this.solucao = dados.solucao();
        }
    }
}
