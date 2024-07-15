package br.com.alura.ForumHub.service.validacoes.resposta;

import br.com.alura.ForumHub.infra.dto.resposta.DadosCadastroResposta;

public interface ValidadorCadastroResposta {

    void validar(DadosCadastroResposta dados);
}
