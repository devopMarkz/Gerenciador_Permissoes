package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.EmpresaPerfil;
import com.github.devopMarkz.gerenciador_permissoes.model.EmpresaPerfilPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmpresaPerfilRepository extends JpaRepository<EmpresaPerfil, EmpresaPerfilPK> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_empresa_perfis WHERE empresa_id = :empresaId", nativeQuery = true)
    void deleteAllByEmpresaId(@Param("empresaId") Long empresaId);

    @Query("SELECT obj FROM EmpresaPerfil obj WHERE obj.empresa.id = :empresaId AND obj.ativo = true")
    List<EmpresaPerfil> buscarPorEmpresaId(@Param("empresaId") Long empresaId);

}
