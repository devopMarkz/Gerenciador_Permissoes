package com.github.devopMarkz.gerenciador_permissoes.controller.admin;

import com.github.devopMarkz.gerenciador_permissoes.dto.ModuloCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.ModuloDetalheDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.ModuloResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PermissaoCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.service.ModuloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.github.devopMarkz.gerenciador_permissoes.util.GeradorUri.gerarURI;

@RestController
@RequestMapping("/admin/modulos")
@PreAuthorize("hasRole('ADMIN')")
public class ModuloAdminController {

    private final ModuloService moduloService;

    public ModuloAdminController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Void> criarModulo(@RequestBody @Valid ModuloCreateDTO dto) {
        Long id = moduloService.criarModulo(dto);
        return ResponseEntity.created(gerarURI(id)).build();
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ModuloResponseDTO>> listar() {
        return ResponseEntity.ok(moduloService.listar());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ModuloDetalheDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.buscarPorId(id));
    }

    // UPLOAD ICON
    @PostMapping("/{id}/imagem")
    public ResponseEntity<Void> atribuirImagem(
            @PathVariable Long id,
            @RequestParam("imagem") MultipartFile imagem
    ) throws IOException {
        moduloService.atribuirImagem(id, imagem);
        return ResponseEntity.noContent().build();
    }

    // CREATE PERMISSAO
    @PostMapping("/{id}/permissoes")
    public ResponseEntity<Void> criarPermissao(
            @PathVariable Long id,
            @RequestBody @Valid PermissaoCreateDTO dto
    ) {
        moduloService.atribuirPermissaoAModulo(id, dto);
        return ResponseEntity.status(201).build();
    }
}
