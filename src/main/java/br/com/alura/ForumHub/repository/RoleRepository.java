package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.infra.model.User.ERole;
import br.com.alura.ForumHub.infra.model.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
