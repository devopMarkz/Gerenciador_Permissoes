package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilDetalheDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PermissaoResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.PerfilMapper;
import com.github.devopMarkz.gerenciador_permissoes.mapper.PermissaoMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.Perfil;
import com.github.devopMarkz.gerenciador_permissoes.model.PerfilPermissao;
import com.github.devopMarkz.gerenciador_permissoes.model.Permissao;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilPermissaoRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PerfilRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.PermissaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;
    private final PerfilMapper perfilMapper;
    private final PermissaoRepository permissaoRepository;
    private final PerfilPermissaoRepository perfilPermissaoRepository;
    private final PermissaoMapper permissaoMapper;

    public PerfilService(PerfilRepository perfilRepository, PerfilMapper perfilMapper, PermissaoRepository permissaoRepository, PerfilPermissaoRepository perfilPermissaoRepository, PermissaoMapper permissaoMapper) {
        this.perfilRepository = perfilRepository;
        this.perfilMapper = perfilMapper;
        this.permissaoRepository = permissaoRepository;
        this.perfilPermissaoRepository = perfilPermissaoRepository;
        this.permissaoMapper = permissaoMapper;
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
        perfil.getPermissoes().clear();

        Set<Permissao> permissoes = permissaoRepository.findByIdIn(permissoesId);

        for (Permissao permissao : permissoes) {
            perfil.getPermissoes().add(new PerfilPermissao(perfil, permissao));
        }

        perfilRepository.save(perfil);
    }

    @Transactional(readOnly = true)
    public List<PerfilDetalheDTO> listar() {
        return perfilRepository.findAll()
                .stream()
                .map(p -> {
                    Set<PermissaoResponseDTO> permissoes = p.getPermissoes()
                            .stream()
                            .map(pp -> permissaoMapper.toResponseDTO(pp.getPermissao()))
                            .collect(Collectors.toSet());

                    return new PerfilDetalheDTO(p.getId(), p.getNome(), p.getDescricao(), permissoes);
                })
                .toList();
    }

    @Transactional(readOnly = true)
    public PerfilDetalheDTO buscarPorId(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Perfil inexistente."));

        Set<PermissaoResponseDTO> permissoes = perfil.getPermissoes()
                .stream()
                .map(pp -> permissaoMapper.toResponseDTO(pp.getPermissao()))
                .collect(Collectors.toSet());

        return new PerfilDetalheDTO(
                perfil.getId(),
                perfil.getNome(),
                perfil.getDescricao(),
                permissoes
        );
    }


}
