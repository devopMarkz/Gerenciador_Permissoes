package com.github.devopMarkz.gerenciador_permissoes.dto;

import java.util.Date;

public record TokenDTO(
        String access_token,
        Date expires_at
) { }
