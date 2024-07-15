package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.infra.dto.resposta.DadosAtualizaResposta;
import br.com.alura.ForumHub.infra.dto.resposta.DadosCadastroResposta;
import br.com.alura.ForumHub.infra.dto.resposta.DadosDetalhamentoResposta;
import br.com.alura.ForumHub.infra.model.Resposta.Resposta;
import br.com.alura.ForumHub.infra.security.ValidacaoException;
import br.com.alura.ForumHub.repository.RespostaRepository;
import br.com.alura.ForumHub.repository.TopicoRepository;
import br.com.alura.ForumHub.service.validacoes.resposta.ValidadorCadastroResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TopicosManager topicosManager;

    @Autowired
    private List<ValidadorCadastroResposta> validadoresCadastro;

    public DadosDetalhamentoResposta cadastrar(DadosCadastroResposta dados) {

        var user = usuarioService.procuraUser(dados.idUsuario());
        usuarioService.confereIdentidade(dados.idUsuario());

        var topico = topicosManager.procuraTopico(dados.idTopico());

        validadoresCadastro.forEach(v -> v.validar(dados));

        var resposta = respostaRepository.save(new Resposta(dados, user, topico));
        return new DadosDetalhamentoResposta(resposta);
    }

    public DadosDetalhamentoResposta atualizar(DadosAtualizaResposta dados) {

        var resposta = procuraResposta(dados.id());
        usuarioService.confereIdentidade(resposta.getUsuario().getId());

        DadosCadastroResposta dadosRespostaNova = new DadosCadastroResposta(
                dados.mensagem(),
                dados.solucao(),
                resposta.getUsuario().getId(),
                resposta.getTopico().getId()
        );
        validadoresCadastro.forEach(v -> v.validar(dadosRespostaNova));

        resposta.atualizarInformacoes(dados);

        return new DadosDetalhamentoResposta(resposta);
    }

    public void deletar(Long id) {

        var resposta = procuraResposta(id);
        usuarioService.confereIdentidade(resposta.getUsuario().getId());
        respostaRepository.deleteById(id);
    }

    private Resposta procuraResposta(Long id) {

        var optionalResposta = respostaRepository.findById(id);
        if (optionalResposta.isEmpty()) {
            throw new ValidacaoException("Id da Resposta informada não existe!");
        }

        return optionalResposta.get();
    }
}
