package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    @Query(value = "SELECT * FROM tb_permissao WHERE id IN :permissoesId", nativeQuery = true)
    Set<Permissao> buscarPermissoesPorId(@Param("permissoesId") Set<Integer> permissoesId);

}
