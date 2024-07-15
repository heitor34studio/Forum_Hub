package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.infra.dto.user.DadosCadastroUser;
import br.com.alura.ForumHub.infra.dto.user.DadosDetalhamentoUser;
import br.com.alura.ForumHub.infra.model.User.Usuario;
import br.com.alura.ForumHub.infra.security.ValidacaoException;
import br.com.alura.ForumHub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoUser> createUsuario(@RequestBody @Valid DadosCadastroUser dados,
                                                               @RequestParam Set<String> roles,
                                                               UriComponentsBuilder uriBuilder) {

        if (!Objects.equals(dados.senha(), dados.senhaIgual())) {
            throw new ValidacaoException("As senhas não coincidem.");
        }

        Usuario usuario = new Usuario(dados);

        usuario.setSenha(passwordEncoder.encode(dados.senha()));

        DadosDetalhamentoUser dto = usuarioService.saveUsuario(usuario, roles);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
