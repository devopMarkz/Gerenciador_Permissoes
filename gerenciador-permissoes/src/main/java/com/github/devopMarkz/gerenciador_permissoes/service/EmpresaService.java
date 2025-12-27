package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.EmpresaMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.Empresa;
import com.github.devopMarkz.gerenciador_permissoes.model.EmpresaModulo;
import com.github.devopMarkz.gerenciador_permissoes.model.Modulo;
import com.github.devopMarkz.gerenciador_permissoes.repository.EmpresaModuloRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.EmpresaRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.ModuloRepository;
import com.github.devopMarkz.gerenciador_permissoes.util.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class EmpresaService extends BaseService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;
    private final ModuloRepository moduloRepository;
    private final EmpresaModuloRepository empresaModuloRepository;

    public EmpresaService(EmpresaRepository empresaRepository, EmpresaMapper empresaMapper, ModuloRepository moduloRepository, EmpresaModuloRepository empresaModuloRepository) {
        this.empresaRepository = empresaRepository;
        this.empresaMapper = empresaMapper;
        this.moduloRepository = moduloRepository;
        this.empresaModuloRepository = empresaModuloRepository;
    }

    @Transactional
    public Long criarEmpresa(EmpresaCreateDTO dto){
        Empresa empresa = empresaMapper.toEntity(dto);
        empresaRepository.save(empresa);
        return empresa.getId();
    }

    @Transactional(readOnly = true)
    public Page<EmpresaResponseDTO> empresaResponseDTOS(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Empresa> empresas = empresaRepository.buscarEmpresas(pageable);
        return empresas.map(empresaMapper::toResponseDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void associarEmpresaAModulos(Long empresaId, Set<Long> modulosIds){
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa inexistente."));

        empresaModuloRepository.deleteAllByEmpresaId(empresaId);

        Set<Modulo> modulos = moduloRepository.findByIdIn(modulosIds);

        for (Modulo modulo : modulos){
            empresa.getModulos().add(new EmpresaModulo(empresa, modulo));
        }

        empresaRepository.save(empresa);
    }

    public void associarEmpresaAPerfis(Long empresaId, Set<Long> perfisIds){
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new IllegalArgumentException("Empresa inexistente."));


    }

}
