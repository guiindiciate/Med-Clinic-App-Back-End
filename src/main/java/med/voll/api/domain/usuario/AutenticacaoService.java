package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Anotação indicando que esta classe é um componente gerenciado pelo Spring (um serviço)
@Service
// Implementação da interface UserDetailsService do Spring Security
public class AutenticacaoService implements UserDetailsService {

    // Injeção de dependência do repositório de usuários
    @Autowired
    private UsuarioRepository repository;

    // Método da interface UserDetailsService para carregar detalhes do usuário pelo nome de usuário
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Chama o método findByLogin do repositório para buscar um usuário pelo nome de usuário
        return repository.findByLogin(username);
    }
}