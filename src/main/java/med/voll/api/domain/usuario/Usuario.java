package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// Anotações para mapeamento da entidade no banco de dados
@Table(name = "usuarios")
@Entity(name = "Usuario")
// Anotação Lombok para gerar automaticamente métodos getters, construtores e equals/hashCode
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
// Implementação da interface UserDetails do Spring Security
public class Usuario implements UserDetails {

    // Anotações para mapeamento de colunas no banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    // Método da interface UserDetails para obter as autoridades do usuário
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorna uma lista com uma autoridade padrão "ROLE_USER"
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    // Método da interface UserDetails para obter a senha do usuário
    @Override
    public String getPassword() {
        return senha;
    }

    // Método da interface UserDetails para obter o nome de usuário do usuário
    @Override
    public String getUsername() {
        return login;
    }

    // Métodos da interface UserDetails para verificar o estado da conta do usuário
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}