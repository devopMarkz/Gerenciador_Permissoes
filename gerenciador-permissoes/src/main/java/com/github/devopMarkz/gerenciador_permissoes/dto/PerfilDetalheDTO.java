package com.github.devopMarkz.gerenciador_permissoes.dto;

import java.util.Set;

public record PerfilDetalheDTO(
        Long id,
        String nome,
        String descricao,
        Set<PermissaoResponseDTO> permissoes
) {}
