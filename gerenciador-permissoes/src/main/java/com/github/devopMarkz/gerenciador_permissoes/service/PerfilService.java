package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.PerfilMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.Perfil;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;

    public PerfilService(PerfilRepository perfilRepository, PerfilMapper perfilMapper) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
    }

    @Transactional
    public Long criarPerfil(PerfilCreateDTO dto){
        Perfil perfil = perfilMapper.toEntity(dto);

        perfilRepository.save(perfil);

        return perfil.getId();
    }

}
