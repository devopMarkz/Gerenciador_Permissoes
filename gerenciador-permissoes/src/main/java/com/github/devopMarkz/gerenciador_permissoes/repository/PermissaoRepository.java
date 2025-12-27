package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    Set<Permissao> findByIdIn(Collection<Long> ids);

}
