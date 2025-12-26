package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.ModuloCreateDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.ModuloMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.Modulo;
import com.github.devopMarkz.gerenciador_permissoes.repository.ModuloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Service
public class ModuloService {

    private final ModuloRepository moduloRepository;
    private final ModuloMapper moduloMapper;

    public ModuloService(ModuloRepository moduloRepository, ModuloMapper moduloMapper) {
        this.moduloRepository = moduloRepository;
        this.moduloMapper = moduloMapper;
    }

    @Transactional
    public Long criarModulo(ModuloCreateDTO dto){
        Modulo modulo = moduloMapper.toEntity(dto);
        moduloRepository.save(modulo);
        return modulo.getId();
    }

    @Transactional
    public void atribuirImagem(Long id, MultipartFile imagem) throws IOException {
        if (imagem == null || imagem.isEmpty()) {
            throw new IllegalArgumentException("Imagem não informada.");
        }

        String contentType = imagem.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Arquivo enviado não é uma imagem válida.");
        }

        Modulo modulo = moduloRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Módulo inexistente."));

        String base64 = Base64.getEncoder().encodeToString(imagem.getBytes());
        String dataUri = "data:" + contentType + ";base64," + base64;

        modulo.setIcone(dataUri);
    }
    
}
