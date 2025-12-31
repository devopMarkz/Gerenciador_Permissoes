package com.github.devopMarkz.gerenciador_permissoes.controller.admin;

import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilDetalheDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.github.devopMarkz.gerenciador_permissoes.util.GeradorUri.gerarURI;

@RestController
@RequestMapping("/admin/perfis")
public class PerfilAdminController {

    private final PerfilService perfilService;

    public PerfilAdminController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @PostMapping
    public ResponseEntity<Void> criarPerfil(@RequestBody @Valid PerfilCreateDTO dto) {
        Long id = perfilService.criarPerfil(dto);
        return ResponseEntity.created(gerarURI(id)).build();
    }

    @GetMapping
    public ResponseEntity<List<PerfilDetalheDTO>> listar() {
        return ResponseEntity.ok(perfilService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDetalheDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(perfilService.buscarPorId(id));
    }

    @PutMapping("/{id}/permissoes")
    public ResponseEntity<Void> associarPermissoes(
            @PathVariable Long id,
            @RequestBody Set<Long> permissoesIds
    ) {
        perfilService.associarPerfilAPermissoes(id, permissoesIds);
        return ResponseEntity.noContent().build();
    }
}

