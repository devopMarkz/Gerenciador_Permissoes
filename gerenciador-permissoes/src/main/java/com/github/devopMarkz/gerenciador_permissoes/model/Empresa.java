package com.github.devopMarkz.gerenciador_permissoes.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_empresa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "razao_social")
    private String razaoSocial;

    @Column(name = "cnpj", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String cnpj;

    @Column(name = "ativa", nullable = false)
    private Boolean ativa = Boolean.TRUE;

    @Column(name = "observacoes")
    private String observacoes;

    @CreationTimestamp
    @Column(name = "criado_em", insertable = false, updatable = false)
    private LocalDateTime criadoEm;

    @CreationTimestamp
    @Column(name = "atualizado_em ", insertable = false)
    private LocalDateTime atualizadoEm;

}
