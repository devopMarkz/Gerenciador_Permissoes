package com.github.devopMarkz.gerenciador_permissoes.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.devopMarkz.gerenciador_permissoes.dto.TokenDTO;
import com.github.devopMarkz.gerenciador_permissoes.model.Usuario;
import com.github.devopMarkz.gerenciador_permissoes.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    private final String SIGNATURE_TOKEN = "abcd";
    private final String ISSUER = "Gerenciador Permissões API";
    private final UsuarioRepository usuarioRepository;
    private Algorithm algorithm;

    public TokenService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostConstruct
    public void encriptarSecret() {
        algorithm = Algorithm.HMAC256(SIGNATURE_TOKEN);
    }

    public TokenDTO obterToken(Usuario usuario){
        String token = gerarToken(usuario);
        Date dataExpiracaoToken = obterDataExpiracao(token);
        return new TokenDTO(token, dataExpiracaoToken);
    }

    @Transactional(readOnly = true)
    public Usuario validarUsuario(String token){
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();

        DecodedJWT decodedJWT = verifier.verify(token);

        String login = decodedJWT.getSubject();

        return usuarioRepository.findUsuarioByEmail(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário inexistente."));
    }

    private String gerarToken(Usuario usuario){
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(usuario.getUsername())
                .withClaim("Role", usuario.getRole().name())
                .withIssuedAt(new Date())
                .withExpiresAt(Instant.now().plus(4, ChronoUnit.HOURS))
                .sign(algorithm);
    }

    private Date obterDataExpiracao(String token){
        return JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
                .getExpiresAt();
    }

}
