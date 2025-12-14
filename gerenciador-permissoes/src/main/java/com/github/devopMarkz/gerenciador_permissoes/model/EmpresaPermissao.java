package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_empresa_permissoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmpresaPermissao {

    @EmbeddedId
    private EmpresaPermissaoPK id = new EmpresaPermissaoPK();

    @ManyToOne
    @MapsId("empresaId")
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @MapsId("permissaoId")
    @JoinColumn(name = "permissao_id", nullable = false)
    private Permissao permissao;

    @Column(name = "permitido", nullable = false)
    private Boolean permitido;

    @CreationTimestamp
    @Column(name = "criado_em", insertable = false, updatable = false)
    private Instant criadoEm;
}