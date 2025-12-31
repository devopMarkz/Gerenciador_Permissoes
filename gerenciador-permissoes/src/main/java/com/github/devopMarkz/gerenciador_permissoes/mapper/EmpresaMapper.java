package com.github.devopMarkz.gerenciador_permissoes.mapper;

import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EmpresaMapper {

    public abstract Empresa toEntity(EmpresaCreateDTO dto);

    @Mapping(target = "permissoesIds", expression = "java(getPermissoesIds(empresa))")
    @Mapping(target = "perfisIds", expression = "java(getPerfisIds(empresa))")
    @Mapping(target = "modulosIds", expression = "java(getModulosIds(empresa))")
    public abstract EmpresaResponseDTO toResponseDTO(Empresa empresa);

    protected Set<Long> getPermissoesIds(Empresa empresa) {
        return empresa.getPermissoes()
                .stream()
                .map(empresaPermissao -> empresaPermissao.getPermissao().getId())
                .collect(Collectors.toSet());
    }

    protected Set<Long> getPerfisIds(Empresa empresa) {
        return empresa.getPerfis()
                .stream()
                .map(empresaPerfil -> empresaPerfil.getPerfil().getId())
                .collect(Collectors.toSet());
    }

    protected Set<Long> getModulosIds(Empresa empresa) {
        return empresa.getModulos()
                .stream()
                .map(empresaModulo -> empresaModulo.getModulo().getId())
                .collect(Collectors.toSet());
    }

}
