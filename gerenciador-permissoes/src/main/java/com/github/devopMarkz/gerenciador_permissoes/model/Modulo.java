package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "tb_modulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(name = "icone")
    private String icone;

    @Column(columnDefinition = "integer default 0")
    private Integer ordem;

    @CreationTimestamp
    @Column(name = "criado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em", columnDefinition = "TIMESTAMPTZ DEFAULT now()")
    private Instant atualizadoEm;

    // Relacionamento One-to-Many com Permissao
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Permissao> permissoes;

    // Relacionamento Many-to-Many com Empresa (via tb_empresa_modulos)
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpresaModulo> empresasModulos;
}
