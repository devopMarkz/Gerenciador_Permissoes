package com.github.devopMarkz.gerenciador_permissoes.controller.publico;

import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PerfisRequestDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PermissaoResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.service.AuthService;
import com.github.devopMarkz.gerenciador_permissoes.service.ModuloService;
import com.github.devopMarkz.gerenciador_permissoes.service.PerfilService;
import com.github.devopMarkz.gerenciador_permissoes.service.PermissaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/public/empresas")
public class EmpresaPublicController {

    private final PerfilService perfilService;
    private final ModuloService moduloService;
    private final PermissaoService permissaoService;
    private final AuthService authService;

    public EmpresaPublicController(PerfilService perfilService, ModuloService moduloService, PermissaoService permissaoService, AuthService authService) {
        this.perfilService = perfilService;
        this.moduloService = moduloService;
        this.permissaoService = permissaoService;
        this.authService = authService;
    }

    @GetMapping("/{id}/perfis")
    public ResponseEntity<List<PerfilResponseDTO>> buscarPerfisPorEmpresa(@PathVariable Long id){
        List<PerfilResponseDTO> perfis = perfilService.buscarPerfisPorEmpresa(id);
        return ResponseEntity.ok(perfis);
    }

//    @GetMapping("/{id}/modulos")
//    public ResponseEntity<List<ModuloResponseDTO>> buscarModulosPorEmpresa(@PathVariable Long id){
//        List<ModuloResponseDTO> modulos = moduloService.buscarModulosPorEmpresa(id);
//        return ResponseEntity.ok(modulos);
//    }

    @PostMapping("/{id}/permissoes")
    public ResponseEntity<Set<PermissaoResponseDTO>> listarPermissoes(
            @PathVariable Long id,
            @RequestParam(name = "tipo", defaultValue = "acao") String tipo,
            @RequestBody PerfisRequestDTO request
    ) {
        Set<PermissaoResponseDTO> permissoes = permissaoService.listarPermissoes(id, tipo, request.getPerfisIds());

        return ResponseEntity.ok(permissoes);
    }

}
