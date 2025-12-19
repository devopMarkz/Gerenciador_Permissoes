package com.github.devopMarkz.gerenciador_permissoes.controller;

import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.devopMarkz.gerenciador_permissoes.util.GeradorUri.gerarURI;

@RestController
@RequestMapping("/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Void> criarPerfil(@RequestBody @Valid PerfilCreateDTO dto){
        Long id = perfilService.criarPerfil(dto);
        return ResponseEntity.created(gerarURI(id)).build();
    }

}
