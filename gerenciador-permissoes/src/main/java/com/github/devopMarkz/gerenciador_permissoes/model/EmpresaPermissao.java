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
    @EqualsAndHashCode.Include
    private EmpresaPermissaoPK id;

    @ManyToOne
    @MapsId("empresaId")
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @MapsId("permissaoId")
    @JoinColumn(name = "permissao_id", nullable = false)
    private Permissao permissao;

    @Column(name = "permitido", nullable = false)
    private Boolean permitido = Boolean.TRUE;

    @CreationTimestamp
    @Column(name = "criado_em", updatable = false)
    private Instant criadoEm;

    public EmpresaPermissao(Empresa empresa, Permissao permissao, Boolean permitido) {
        this.empresa = empresa;
        this.permissao = permissao;
        this.permitido = permitido;
        this.id = new EmpresaPermissaoPK(
                empresa.getId(),
                permissao.getId()
        );
    }
}
