package com.github.devopMarkz.gerenciador_permissoes.dto;

import java.util.Set;

public record ModuloDetalheDTO(
        Long id,
        String nome,
        String descricao,
        String icone,
        Set<PermissaoResponseDTO> permissoes
) {}
