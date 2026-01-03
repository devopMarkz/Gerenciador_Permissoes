package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Permissao;
import com.github.devopMarkz.gerenciador_permissoes.model.TipoPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    Set<Permissao> findByIdIn(Collection<Long> ids);

    @Query("""
        SELECT DISTINCT p
        FROM Permissao p
        JOIN p.perfis pp
        JOIN pp.perfil pf
        WHERE pf.id IN :perfisIds
          AND p.tipo = :tipo
          AND NOT EXISTS (
              SELECT 1
              FROM EmpresaPermissao ep
              WHERE ep.empresa.id = :empresaId
                AND ep.permissao = p
                AND ep.permitido = false
          )
    """)
    Set<Permissao> buscarPermissoesPorPerfis(
            @Param("empresaId") Long empresaId,
            @Param("tipo") TipoPermissao tipo,
            @Param("perfisIds") Set<Long> perfisIds
    );

}
