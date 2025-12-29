package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(columnDefinition = "boolean default true")
    private Boolean ativa = Boolean.TRUE;

    @Column(name = "observacoes")
    private String observacoes;

    @CreationTimestamp
    @Column(name = "criado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant atualizadoEm;

    // Relacionamento One-to-Many com Usuario
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuarios = new HashSet<>();

    // Relacionamento Many-to-Many com Perfil (via tb_empresa_perfis)
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpresaPerfil> perfis = new HashSet<>();

    // Relacionamento Many-to-Many com Modulo (via tb_empresa_modulos)
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpresaModulo> modulos = new HashSet<>();

    // Relacionamento Many-to-Many com Permissao (via tb_empresa_permissoes)
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpresaPermissao> permissoes = new HashSet<>();
}