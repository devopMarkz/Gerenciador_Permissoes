package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_perfil")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @CreationTimestamp
    @Column(name = "criado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant atualizadoEm;

    // Relacionamento Many-to-Many com Permissao (via tb_perfil_permissao)
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PerfilPermissao> permissoes = new HashSet<>();

    // Relacionamento Many-to-Many com Empresa (via tb_empresa_perfis)
    @OneToMany(mappedBy = "perfil", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpresaPerfil> empresasPerfis = new HashSet<>();
}