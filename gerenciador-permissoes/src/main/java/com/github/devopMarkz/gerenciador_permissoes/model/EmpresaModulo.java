package com.github.devopMarkz.gerenciador_permissoes.model;

import lombok.*;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_empresa_modulos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmpresaModulo implements Serializable {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private EmpresaModuloPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empresaId")
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("moduloId")
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    @Column(columnDefinition = "boolean default true")
    private Boolean ativo = Boolean.TRUE;

    public EmpresaModulo(Empresa empresa, Modulo modulo) {
        this.empresa = empresa;
        this.modulo = modulo;
        this.id = new EmpresaModuloPK(
                empresa.getId(),
                modulo.getId()
        );
    }
}
