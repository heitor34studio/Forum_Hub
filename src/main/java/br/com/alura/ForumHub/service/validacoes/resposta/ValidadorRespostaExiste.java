package br.com.alura.ForumHub.service.validacoes.resposta;

import br.com.alura.ForumHub.infra.dto.resposta.DadosCadastroResposta;
import br.com.alura.ForumHub.infra.security.ValidacaoException;
import br.com.alura.ForumHub.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorRespostaExiste implements ValidadorCadastroResposta{

    @Autowired
    private RespostaRepository respostaRepository;

    @Override
    public void validar(DadosCadastroResposta dados) {

        if (dados.mensagem() == null || dados.solucao() == null) {
            return;
        }

        var mensagemJaExiste = respostaRepository.existsByMensagem(dados.mensagem());
        var solucaoJaExiste = respostaRepository.existsBySolucao(dados.solucao());

        if (solucaoJaExiste || mensagemJaExiste) {
            throw new ValidacaoException("Esta resposta já existe.");
        }
    }
}
