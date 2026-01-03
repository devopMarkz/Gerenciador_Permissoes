package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.PermissaoResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.PermissaoMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.TipoPermissao;
import com.github.devopMarkz.gerenciador_permissoes.model.Usuario;
import com.github.devopMarkz.gerenciador_permissoes.repository.ModuloRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilPermissaoRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PermissaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;
    private final PerfilRepository perfilRepository;
    private final PermissaoMapper permissaoMapper;
    private final PerfilPermissaoRepository perfilPermissaoRepository;
    private final ModuloRepository moduloRepository;
    private final AuthService authService;

    public PermissaoService(PermissaoRepository permissaoRepository, PerfilRepository perfilRepository, PermissaoMapper permissaoMapper, PerfilPermissaoRepository perfilPermissaoRepository, ModuloRepository moduloRepository, AuthService authService) {
        this.permissaoRepository = permissaoRepository;
        this.perfilRepository = perfilRepository;
        this.permissaoMapper = permissaoMapper;
        this.perfilPermissaoRepository = perfilPermissaoRepository;
        this.moduloRepository = moduloRepository;
        this.authService = authService;
    }

    @Transactional(readOnly = true)
    public Set<PermissaoResponseDTO> listarPermissoes(
            Long empresaId,
            String tipo,
            Set<Long> perfisIds
    ) {
        Usuario usuario = authService.obterUsuarioLogado();

        if (usuario.getEmpresa() == null || !Objects.equals(usuario.getEmpresa().getId(), empresaId)) {
            throw new IllegalArgumentException("Usuário não está associado a essa empresa.");
        }

        if("acao".equals(tipo)) tipo = "ACAO";
        else if("modulo".equals(tipo)) tipo = "MODULO";
        else throw new IllegalArgumentException("Aceitamos apenas acao e modulo no parametro de url.");

        TipoPermissao tipoPermissao = TipoPermissao.valueOf(tipo);

        return permissaoRepository
                .buscarPermissoesPorPerfis(empresaId, tipoPermissao, perfisIds)
                .stream()
                .map(permissaoMapper::toResponseDTO)
                .collect(Collectors.toSet());
    }

}
