package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PerfilPermissaoPK implements Serializable {

    @Column(name = "perfil_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long perfilId;

    @Column(name = "permissao_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long permissaoId;
}
