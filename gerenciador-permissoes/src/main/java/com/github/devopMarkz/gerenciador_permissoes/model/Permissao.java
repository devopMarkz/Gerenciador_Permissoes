package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_permissao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "chave", nullable = false, unique = true)
    private String chave;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "link", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String link;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id", nullable = false)
    private Modulo modulo;

    // Relacionamento Many-to-Many com Perfil (via tb_perfil_permissao)
    @OneToMany(mappedBy = "permissao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PerfilPermissao> perfis = new HashSet<>();

    // Relacionamento Many-to-Many com Empresa (via tb_empresa_permissoes)
    @OneToMany(mappedBy = "permissao", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EmpresaPermissao> empresasPermissoes = new HashSet<>();

}
