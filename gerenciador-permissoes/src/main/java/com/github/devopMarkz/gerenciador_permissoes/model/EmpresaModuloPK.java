package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmpresaModuloPK {

    @Column(name = "empresa_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long empresaId;

    @Column(name = "modulo_id", nullable = false)
    @EqualsAndHashCode.Include
    private Long moduloId;

}
