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
    private EmpresaModuloPK id = new EmpresaModuloPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    @MapsId("empresaId")
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id")
    @MapsId("moduloId")
    private Modulo modulo;

    @Column(columnDefinition = "boolean default true")
    private Boolean ativo = Boolean.TRUE;
}