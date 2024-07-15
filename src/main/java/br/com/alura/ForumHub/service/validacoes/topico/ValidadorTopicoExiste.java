package br.com.alura.ForumHub.service.validacoes.topico;

import br.com.alura.ForumHub.infra.dto.topico.DadosCadastroTopico;
import br.com.alura.ForumHub.infra.security.ValidacaoException;
import br.com.alura.ForumHub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoExiste implements ValidadorCadastroTopico{

    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DadosCadastroTopico dados) {

        if (dados.titulo() == null || dados.mensagem() == null) {
            return;
        }

        var tituloJaExiste = topicoRepository.existsByTitulo(dados.titulo());
        var mensagemJaExiste = topicoRepository.existsByMensagem(dados.mensagem());

        if(tituloJaExiste || mensagemJaExiste) {
            throw new ValidacaoException("Este tópico já existe.");
        }
    }
}
