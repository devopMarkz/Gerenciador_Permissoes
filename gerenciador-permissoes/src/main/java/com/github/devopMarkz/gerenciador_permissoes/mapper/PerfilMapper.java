package com.github.devopMarkz.gerenciador_permissoes.mapper;

import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.PerfilResponseDTO;
import com.github.devopMarkz.gerenciador_permissoes.model.Perfil;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PerfilMapper {

    public abstract Perfil toEntity(PerfilCreateDTO dto);

    public abstract PerfilResponseDTO toRespondeDTO(Perfil perfil);

}
