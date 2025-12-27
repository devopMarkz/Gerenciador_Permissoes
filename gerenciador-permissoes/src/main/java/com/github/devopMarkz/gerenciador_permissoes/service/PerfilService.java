package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.PerfilMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.Perfil;
import com.github.devopMarkz.gerenciador_permissoes.model.PerfilPermissao;
import com.github.devopMarkz.gerenciador_permissoes.model.Permissao;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilPermissaoRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PermissaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;
    private final PermissaoRepository permissaoRepository;
    private final PerfilPermissaoRepository perfilPermissaoRepository;

    public PerfilService(PerfilRepository perfilRepository, PerfilMapper perfilMapper, PermissaoRepository permissaoRepository, PerfilPermissaoRepository perfilPermissaoRepository) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
        this.permissaoRepository = permissaoRepository;
        this.perfilPermissaoRepository = perfilPermissaoRepository;
    }

    @Transactional
    public Long criarPerfil(PerfilCreateDTO dto){
        Perfil perfil = perfilMapper.toEntity(dto);

        perfilRepository.save(perfil);

        return perfil.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public void associarPerfilAPermissoes(Long perfilId, Set<Long> permissoesId){
        Perfil perfil = perfilRepository.findById(perfilId)
                .orElseThrow(() -> new IllegalArgumentException("Perfil inexistente."));

        perfilPermissaoRepository.deleteAllByPerfilId(perfilId);

        Set<Permissao> permissoes = permissaoRepository.findByIdIn(permissoesId);

        for (Permissao permissao : permissoes) {
            perfil.getPermissoes().add(new PerfilPermissao(perfil, permissao));
        }

        perfilRepository.save(perfil);
    }

}
