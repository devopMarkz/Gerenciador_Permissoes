package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.EmpresaPermissao;
import com.github.devopMarkz.gerenciador_permissoes.model.EmpresaPermissaoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface EmpresaPermissaoRepository
        extends JpaRepository<EmpresaPermissao, EmpresaPermissaoPK> {

    @Query("SELECT obj FROM EmpresaPermissao obj WHERE obj.empresa.id = :empresaId")
    Set<EmpresaPermissao> findByEmpresaId(@Param("empresaId") Long empresaId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_empresa_permissoes WHERE empresa_id = :empresaId", nativeQuery = true)
    void deleteAllByEmpresaId(@Param("empresaId") Long empresaId);
}