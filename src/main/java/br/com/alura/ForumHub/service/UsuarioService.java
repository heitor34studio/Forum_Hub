package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.infra.dto.user.DadosDetalhamentoUser;
import br.com.alura.ForumHub.infra.model.User.ERole;
import br.com.alura.ForumHub.infra.model.User.Role;
import br.com.alura.ForumHub.infra.model.User.Usuario;
import br.com.alura.ForumHub.infra.security.TokenService;
import br.com.alura.ForumHub.infra.security.ValidacaoException;
import br.com.alura.ForumHub.repository.RoleRepository;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    public DadosDetalhamentoUser saveUsuario(Usuario usuario, Set<String> strRoles) {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        usuario.setRoles(roles);
        return new DadosDetalhamentoUser(usuarioRepository.save(usuario));
    }

    public Usuario procuraUser(Long id) {

        var user = usuarioRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ValidacaoException("Id do usuário informado não existe!");
        }
    }

    public void confereIdentidade(Long idDoUserInformado) {

        var userInformado = usuarioRepository.findById(idDoUserInformado);
        if (userInformado.isEmpty()){
            throw new ValidacaoException("Usuário informado não existe!");
        }
        var emailDoUserLogado = TokenService.getCurrentUserId();
        if (!Objects.equals(emailDoUserLogado, userInformado.get().getEmail())) {
            throw new ValidacaoException("Usuário informado não é o mesmo do que fez login!");
        }
    }
}
