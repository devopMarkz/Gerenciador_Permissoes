package com.github.devopMarkz.gerenciador_permissoes.controller;

import com.github.devopMarkz.gerenciador_permissoes.dto.LoginDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.TokenDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.UsuarioDTO;
import com.github.devopMarkz.gerenciador_permissoes.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        TokenDTO tokenDTO = authService.login(loginDTO);
        return ResponseEntity.ok(tokenDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        authService.register(usuarioDTO);
        return ResponseEntity.ok().build();
    }

}
