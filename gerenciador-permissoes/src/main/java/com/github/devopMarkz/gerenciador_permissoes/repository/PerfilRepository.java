package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Perfil;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

    Set<Perfil> findByIdIn(Collection<Long> ids);

    @EntityGraph(attributePaths = {"permissoes", "permissoes.permissao"})
    List<Perfil> findAll();

    @EntityGraph(attributePaths = {"permissoes", "permissoes.permissao"})
    Perfil findWithPermissoesById(Long id);

}
