package com.github.devopMarkz.gerenciador_permissoes.repository;

import com.github.devopMarkz.gerenciador_permissoes.model.PerfilPermissao;
import com.github.devopMarkz.gerenciador_permissoes.model.PerfilPermissaoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilPermissaoRepository extends JpaRepository<PerfilPermissao, PerfilPermissaoPK> {
}
