package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    @Query(value = "SELECT * FROM tb_permissao WHERE id IN :modulosIds", nativeQuery = true)
    Set<Modulo> buscarModulosPorIds(@Param("modulosIds") Set<Long> modulosIds);

}
