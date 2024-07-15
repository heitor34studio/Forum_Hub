package br.com.alura.ForumHub.infra.dto.user;

import br.com.alura.ForumHub.infra.model.User.Usuario;

public record DadosDetalhamentoUser(Long id, String nome, String email) {
    public DadosDetalhamentoUser(Usuario user) {
        this(user.getId(), user.getNome(), user.getEmail());
    }
}
