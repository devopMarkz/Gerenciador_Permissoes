package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.PermissaoCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.PermissaoMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.Perfil;
import com.github.devopMarkz.gerenciador_permissoes.model.PerfilPermissao;
import com.github.devopMarkz.gerenciador_permissoes.model.Permissao;
import com.github.devopMarkz.gerenciador_permissoes.repository.ModuloRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilPermissaoRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PermissaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissaoService {

    private final PermissaoRepository permissaoRepository;
    private final PerfilRepository perfilRepository;
    private final PermissaoMapper permissaoMapper;
    private final PerfilPermissaoRepository perfilPermissaoRepository;
    private final ModuloRepository moduloRepository;

    public PermissaoService(PermissaoRepository permissaoRepository, PerfilRepository perfilRepository, PermissaoMapper permissaoMapper, PerfilPermissaoRepository perfilPermissaoRepository, ModuloRepository moduloRepository) {
        this.permissaoRepository = permissaoRepository;
        this.perfilRepository = perfilRepository;
        this.permissaoMapper = permissaoMapper;
        this.perfilPermissaoRepository = perfilPermissaoRepository;
        this.moduloRepository = moduloRepository;
    }

    @Transactional
    public Long criarPermissao(PermissaoCreateDTO dto, Long moduloId, Long perfilId){
        if(!moduloRepository.existsById(moduloId)) {
            throw new IllegalArgumentException("Módulo inexistente.");
        }

        if(!perfilRepository.existsById(perfilId)){
            throw new IllegalArgumentException("Perfil inexistente.");
        }

        Permissao permissao = permissaoMapper.toEntity(dto);
        permissaoRepository.saveAndFlush(permissao);

        Perfil perfil = perfilRepository.getReferenceById(perfilId);

        PerfilPermissao perfilPermissao = new PerfilPermissao();
        perfilPermissao.setPermissao(permissao);
        perfilPermissao.setPerfil(perfil);
        perfilPermissaoRepository.saveAndFlush(perfilPermissao);

        return null;
    }

}
