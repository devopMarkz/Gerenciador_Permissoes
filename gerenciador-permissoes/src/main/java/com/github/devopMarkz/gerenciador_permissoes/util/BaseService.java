package com.github.devopMarkz.gerenciador_permissoes.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class BaseService {

    public Pageable gerarPaginacao(int pageNumber, int pageSize, Sort sort){
        return PageRequest.of(pageNumber, pageSize, sort);
    }

}
