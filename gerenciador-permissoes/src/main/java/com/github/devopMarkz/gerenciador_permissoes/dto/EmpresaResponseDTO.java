package com.github.devopMarkz.gerenciador_permissoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaResponseDTO {

    private Long id;
    private String nome;
    private String razaoSocial;
    private String cnpj;
    private Boolean ativa;
    private String observacoes;
    private Set<Long> perfisIds;
    private Set<Long> permissoesIds;
    private Set<Long> modulosIds;

}
