package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Declaração da classe do controlador para autenticação de usuários
@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    // Injeção de dependência do gerenciador de autenticação
    @Autowired
    private AuthenticationManager manager;

    // Injeção de dependência do serviço de tokens
    @Autowired
    private TokenService tokenService;

    // Endpoint para efetuar o login e gerar um token JWT
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // Criação de um token de autenticação com base nos dados de login e senha fornecidos
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        // Autenticação do usuário utilizando o gerenciador de autenticação
        var authentication = manager.authenticate(authenticationToken);
        // Geração do token JWT com base no usuário autenticado
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Retorno da resposta com o token JWT
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}