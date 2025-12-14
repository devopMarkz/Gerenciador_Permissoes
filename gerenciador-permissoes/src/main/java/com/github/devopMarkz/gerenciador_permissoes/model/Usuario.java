package com.github.devopMarkz.gerenciador_permissoes.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // Relacionamento Many-to-One com Empresa (opcional)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(columnDefinition = "boolean default true")
    private Boolean ativo;

    @Column(name = "criado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant criadoEm;

    @Column(name = "atualizado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant atualizadoEm;

    public enum Role {
        ROLE_ADMIN,
        ROLE_EMPRESA
    }
}