package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_perfil_permissao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PerfilPermissao {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private PerfilPermissaoPK id;

    @ManyToOne
    @MapsId("perfilId")
    @JoinColumn(name = "perfil_id", nullable = false)
    private Perfil perfil;

    @ManyToOne
    @MapsId("permissaoId")
    @JoinColumn(name = "permissao_id", nullable = false)
    private Permissao permissao;

    public PerfilPermissao(Perfil perfil, Permissao permissao) {
        this.perfil = perfil;
        this.permissao = permissao;
        this.id = new PerfilPermissaoPK(
                perfil.getId(),
                permissao.getId()
        );
    }
}