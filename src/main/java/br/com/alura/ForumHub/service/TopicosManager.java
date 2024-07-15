package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.infra.dto.topico.DadosAtualizaTopico;
import br.com.alura.ForumHub.infra.dto.topico.DadosCadastroTopico;
import br.com.alura.ForumHub.infra.dto.topico.DadosDetalhamentoTopico;
import br.com.alura.ForumHub.infra.model.Topico.Status;
import br.com.alura.ForumHub.infra.model.Topico.Topico;
import br.com.alura.ForumHub.infra.security.TokenService;
import br.com.alura.ForumHub.infra.security.ValidacaoException;
import br.com.alura.ForumHub.repository.CursoRepository;
import br.com.alura.ForumHub.repository.TopicoRepository;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import br.com.alura.ForumHub.service.validacoes.topico.ValidadorCadastroTopico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicosManager {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private List<ValidadorCadastroTopico> validadoresCadastro;

    public DadosDetalhamentoTopico cadastrar(DadosCadastroTopico dados) {

        var user = usuarioService.procuraUser(dados.idUsuario());
        usuarioService.confereIdentidade(dados.idUsuario());

        var curso = cursoService.confereCursoExiste(dados.idCurso());

        validadoresCadastro.forEach(v -> v.validar(dados));

        var topico = new Topico(dados.titulo(), dados.mensagem(), Status.PENDENTE, user, curso, LocalDateTime.now());
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public DadosDetalhamentoTopico atualizar(DadosAtualizaTopico dados) {

        var topico = procuraTopico(dados.id());
        usuarioService.confereIdentidade(topico.getUsuario().getId());

        DadosCadastroTopico dadosTopicoNovo = new DadosCadastroTopico(
                dados.titulo(),
                dados.mensagem(),
                topico.getUsuario().getId(),
                topico.getCurso().getId());

        if (dados.titulo() != null || dados.mensagem() != null) {
            validadoresCadastro.forEach(v -> v.validar(dadosTopicoNovo));
        }

        topico.atualizarInformacoes(dados);

        return new DadosDetalhamentoTopico(topico);
    }

    public void deletar(Long id) {

        var topico = procuraTopico(id);
        usuarioService.confereIdentidade(topico.getUsuario().getId());
        topicoRepository.deleteById(id);
    }



    public Topico procuraTopico(Long id) {

        var optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            throw new ValidacaoException("Id do Tópico informado não existe!");
        }

        return optionalTopico.get();
    }
}
