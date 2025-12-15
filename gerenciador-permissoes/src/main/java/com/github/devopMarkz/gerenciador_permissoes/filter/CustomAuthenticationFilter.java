package com.github.devopMarkz.gerenciador_permissoes.filter;

import com.github.devopMarkz.gerenciador_permissoes.model.Usuario;
import com.github.devopMarkz.gerenciador_permissoes.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public CustomAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extraiTokenDoHeader(request);

        if(token != null) {
            Usuario usuario = tokenService.validarUsuario(token);
            if(usuario.getAtivo()){
                var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String extraiTokenDoHeader(HttpServletRequest request){
        String token = request.getHeader("Authorization");

        if(token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.split(" ")[1];
    }

}
