package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @SuppressWarnings("NullableProblems")
    @EntityGraph(attributePaths = {
            "permissoes", "modulos", "perfis",
            "permissoes.permissao", "modulos.modulo", "perfis.perfil"
    })
    Page<Empresa> findAll(Pageable pageable);

}
