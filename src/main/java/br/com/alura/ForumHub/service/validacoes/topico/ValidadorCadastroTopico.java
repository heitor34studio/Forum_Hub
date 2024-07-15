package br.com.alura.ForumHub.service.validacoes.topico;

import br.com.alura.ForumHub.infra.dto.topico.DadosCadastroTopico;

public interface ValidadorCadastroTopico {

    void validar(DadosCadastroTopico dados);
}
