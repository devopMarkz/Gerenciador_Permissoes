package com.github.devopMarkz.gerenciador_permissoes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissaoCreateDTO {

    private String chave;
    private String nome;
    private String descricao;
    private String link;
    private String tipo;

}
