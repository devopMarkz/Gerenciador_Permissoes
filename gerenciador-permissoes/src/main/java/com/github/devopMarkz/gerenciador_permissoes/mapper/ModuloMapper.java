package com.github.devopMarkz.gerenciador_permissoes.mapper;

import com.github.devopMarkz.gerenciador_permissoes.dto.ModuloCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.ModuloDetalheDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.ModuloResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.model.Modulo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PermissaoMapper.class)
public interface ModuloMapper {

    Modulo toEntity(ModuloCreateDTO dto);

    ModuloResponseDTO toResponseDTO(Modulo modulo);

    ModuloDetalheDTO toDetalheDTO(Modulo modulo);

}
