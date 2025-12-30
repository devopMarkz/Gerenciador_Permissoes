package com.github.devopMarkz.gerenciador_permissoes.controller.admin;

import com.github.devopMarkz.gerenciador_permissoes.dto.*;
import com.github.devopMarkz.gerenciador_permissoes.service.ModuloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static com.github.devopMarkz.gerenciador_permissoes.util.GeradorUri.gerarURI;

@RestController
@RequestMapping("/admin/modulos")
public class ModuloAdminController {

    private final ModuloService moduloService;

    public ModuloAdminController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @PostMapping
    public ResponseEntity<Void> criarModulo(@RequestBody @Valid ModuloCreateDTO dto) {
        Long id = moduloService.criarModulo(dto);
        return ResponseEntity.created(gerarURI(id)).build();
    }

    @GetMapping
    public ResponseEntity<List<ModuloDetalheDTO>> listar() {
        return ResponseEntity.ok(moduloService.listarModulosComPermissoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloDetalheDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.buscarPorId(id));
    }

    @PostMapping("/{id}/imagem")
    public ResponseEntity<Void> atribuirImagem(
            @PathVariable Long id,
            @RequestParam("imagem") MultipartFile imagem
    ) throws IOException {
        moduloService.atribuirImagem(id, imagem);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/permissoes")
    public ResponseEntity<Void> criarPermissao(
            @PathVariable Long id,
            @RequestBody @Valid PermissaoCreateDTO dto
    ) {
        moduloService.atribuirPermissaoAModulo(id, dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}/permissoes")
    public ResponseEntity<Set<PermissaoResponseDTO>> listarPermissoes(@PathVariable Long id) {
        return ResponseEntity.ok(moduloService.listarPermissoes(id));
    }

    @DeleteMapping("/{moduloId}/permissoes/{permissaoId}")
    public ResponseEntity<Void> removerPermissao(
            @PathVariable Long moduloId,
            @PathVariable Long permissaoId
    ) {
        moduloService.removerPermissaoDoModulo(moduloId, permissaoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirModulo(@PathVariable Long id) {
        moduloService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
