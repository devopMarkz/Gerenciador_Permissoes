package com.github.devopMarkz.gerenciador_permissoes.mapper;

import com.github.devopMarkz.gerenciador_permissoes.dto.UsuarioDTO;
import com.github.devopMarkz.gerenciador_permissoes.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO usuarioDTO);

}
