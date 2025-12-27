package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.Empresa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Page<Empresa> buscarEmpresas(Pageable pageable);

}
