package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

// Interface que estende JpaRepository para operações de persistência relacionadas à entidade Usuario
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método personalizado para buscar um UserDetails pelo nome de usuário
    UserDetails findByLogin(String login);
}
