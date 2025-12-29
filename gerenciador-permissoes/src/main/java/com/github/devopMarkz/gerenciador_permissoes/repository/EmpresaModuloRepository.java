package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.EmpresaModulo;
import com.github.devopMarkz.gerenciador_permissoes.model.EmpresaModuloPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmpresaModuloRepository extends JpaRepository<EmpresaModulo, EmpresaModuloPK> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_empresa_modulos WHERE empresa_id = :empresaId", nativeQuery = true)
    void deleteAllByEmpresaId(@Param("empresaId") Long empresaId);

    boolean existsByModuloId(Long moduloId);

}
