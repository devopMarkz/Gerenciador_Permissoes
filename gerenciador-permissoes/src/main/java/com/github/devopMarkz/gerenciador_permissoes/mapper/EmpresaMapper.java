package com.github.devopMarkz.gerenciador_permissoes.mapper;

import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.EmpresaResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.model.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmpresaMapper {

    Empresa toEntity(EmpresaCreateDTO dto);

    EmpresaResponseDTO toResponseDTO(Empresa empresa);

}
