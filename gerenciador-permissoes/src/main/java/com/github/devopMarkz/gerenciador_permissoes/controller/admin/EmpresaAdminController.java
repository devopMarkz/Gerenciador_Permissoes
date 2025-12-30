package com.github.devopMarkz.gerenciador_permissoes.controller.admin;

import com.github.devopMarkz.gerenciador_permissoes.dto.*;
import com.github.devopMarkz.gerenciador_permissoes.service.EmpresaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.devopMarkz.gerenciador_permissoes.util.GeradorUri.gerarURI;

@RestController
@RequestMapping("/admin/empresas")
public class EmpresaAdminController {

    private final EmpresaService empresaService;

    public EmpresaAdminController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Long> criarEmpresa(@RequestBody @Valid EmpresaCreateDTO dto) {
        Long empresaId = empresaService.criarEmpresa(dto);
        return ResponseEntity.created(gerarURI(empresaId)).build();
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaResponseDTO>> listarEmpresas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                empresaService.empresaResponseDTOS(page, size)
        );
    }

    @PutMapping("/{empresaId}/modulos")
    public ResponseEntity<Void> associarModulos(
            @PathVariable Long empresaId,
            @RequestBody EmpresaModuloDTO dto
    ) {
        empresaService.associarEmpresaAModulos(empresaId, dto.getModulosIds());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{empresaId}/perfis")
    public ResponseEntity<Void> associarPerfis(
            @PathVariable Long empresaId,
            @RequestBody EmpresaPerfilDTO dto
    ) {
        empresaService.associarEmpresaAPerfis(empresaId, dto.getPerfisIds());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{empresaId}/permissoes")
    public ResponseEntity<Void> associarPermissoes(
            @PathVariable Long empresaId,
            @RequestBody EmpresaPermissaoDTO dto
    ) {
        empresaService.associarEmpresaAPermissoes(
                empresaId,
                dto.getPermissoesIds(),
                dto.getPermitido()
        );
        return ResponseEntity.noContent().build();
    }
}
