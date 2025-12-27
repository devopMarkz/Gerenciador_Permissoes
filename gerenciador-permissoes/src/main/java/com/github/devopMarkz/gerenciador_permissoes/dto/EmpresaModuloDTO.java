package com.github.devopMarkz.gerenciador_permissoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpresaModuloDTO {
    private Set<Long> modulosIds;
}
