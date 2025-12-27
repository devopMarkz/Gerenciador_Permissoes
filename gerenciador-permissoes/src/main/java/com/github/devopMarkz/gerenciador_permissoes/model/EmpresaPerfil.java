package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_empresa_perfis")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EmpresaPerfil {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private EmpresaPerfilPK id = new EmpresaPerfilPK();

    @ManyToOne
    @MapsId("empresaId")
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ManyToOne
    @MapsId("perfilId")
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @CreationTimestamp
    @Column(name = "atribuido_em", updatable = false)
    private Instant atribuidoEm;

    @Column(name = "observacao")
    private String observacao;

    public EmpresaPerfil(Empresa empresa, Perfil perfil) {
        this.empresa = empresa;
        this.perfil = perfil;
    }
}