package com.github.devopMarkz.gerenciador_permissoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaCreateDTO {

    private String nome;
    private String razaoSocial;
    private String cnpj;
    private String observacoes;

}
