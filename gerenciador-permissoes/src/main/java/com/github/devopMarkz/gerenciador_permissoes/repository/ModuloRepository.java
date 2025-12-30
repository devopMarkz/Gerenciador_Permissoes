package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Modulo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {

    Set<Modulo> findByIdIn(Collection<Long> ids);

    @Query("SELECT obj FROM Modulo obj")
    @EntityGraph(attributePaths = {"permissoes"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Modulo> buscarModulosComPermissoes();

}
