package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.EmpresaMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.*;
import com.github.devopMarkz.gerenciador_permissoes.repository.*;
import com.github.devopMarkz.gerenciador_permissoes.util.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class EmpresaService extends BaseService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;
    private final ModuloRepository moduloRepository;
    private final EmpresaModuloRepository empresaModuloRepository;
    private final PerfilRepository perfilRepository;
    private final EmpresaPerfilRepository empresaPerfilRepository;
    private final EmpresaPermissaoRepository empresaPermissaoRepository;
    private final PermissaoRepository permissaoRepository;

    public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper, ModuloRepository moduloRepository, EmpresaModuloRepository empresaModuloRepository, PerfilRepository perfilRepository, EmpresaPerfilRepository empresaPerfilRepository, EmpresaPermissaoRepository empresaPermissaoRepository, PermissaoRepository permissaoRepository) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
        this.moduloRepository = moduloRepository;
        this.empresaModuloRepository = empresaModuloRepository;
        this.perfilRepository = perfilRepository;
        this.empresaPerfilRepository = empresaPerfilRepository;
        this.empresaPermissaoRepository = empresaPermissaoRepository;
        this.permissaoRepository = permissaoRepository;
    }

    @Transactional
    public Long criarEmpresa(EmpresaCreateDTO dto){
        Empresa empresa = empresaMapper.toEntity(dto);
        empresaRepository.save(empresa);
        return empresa.getId();
    }

    @Transactional(readOnly = true)
    public Page<EmpresaResponseDTO> empresaResponseDTOS(int pageNumber, int pageSize){
        Pageable pageable = gerarPaginacao(pageNumber, pageSize, Sort.unsorted());
        Page<Empresa> empresas = empresaRepository.findAll(pageable);
        return empresas.map(empresaMapper::toResponseDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void associarEmpresaAModulos(Long empresaId, Set<Long> modulosIds) {

        if (modulosIds == null || modulosIds.isEmpty()) {
            throw new IllegalArgumentException("Listagem de módulos não pode estar vazia.");
        }

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa inexistente."));

        empresaModuloRepository.deleteAllByEmpresaId(empresaId);
        empresa.getModulos().clear();

        Set<Modulo> modulos = moduloRepository.findByIdIn(modulosIds);

        if (modulos.size() != modulosIds.size()) {
            throw new IllegalArgumentException("Um ou mais módulos não existem.");
        }

        for (Modulo modulo : modulos) {
            empresa.getModulos().add(new EmpresaModulo(empresa, modulo));
        }

        empresaRepository.save(empresa);
    }


    @Transactional(rollbackFor = Exception.class)
    public void associarEmpresaAPerfis(Long empresaId, Set<Long> perfisIds) {

        if (perfisIds == null || perfisIds.isEmpty()) {
            throw new IllegalArgumentException("Listagem de perfis não pode estar vazia.");
        }

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa inexistente."));

        empresaPerfilRepository.deleteAllByEmpresaId(empresaId);
        empresa.getPerfis().clear();

        Set<Perfil> perfis = perfilRepository.findByIdIn(perfisIds);

        if (perfis.size() != perfisIds.size()) {
            throw new IllegalArgumentException("Um ou mais perfis não existem.");
        }

        for (Perfil perfil : perfis) {
            empresa.getPerfis().add(new EmpresaPerfil(empresa, perfil));
        }

        empresaRepository.save(empresa);
    }


    @Transactional(rollbackFor = Exception.class)
    public void associarEmpresaAPermissoes(Long empresaId, Set<Long> permissoesIds, boolean permitido) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa inexistente."));

        if (permissoesIds == null || permissoesIds.isEmpty()) {
            throw new IllegalArgumentException("Listagem de permissões não pode estar vazia.");
        }

        // remove overrides anteriores
        empresaPermissaoRepository.deleteAllByEmpresaId(empresaId);
        empresa.getPermissoes().clear();

        Set<Permissao> permissoes = permissaoRepository.findByIdIn(permissoesIds);

        for (Permissao permissao : permissoes) {
            EmpresaPermissao ep = new EmpresaPermissao();
            ep.setEmpresa(empresa);
            ep.setPermissao(permissao);
            ep.setPermitido(permitido);

            empresa.getPermissoes().add(ep);
        }

        empresaRepository.save(empresa);
    }


}
