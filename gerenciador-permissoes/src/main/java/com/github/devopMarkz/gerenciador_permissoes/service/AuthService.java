package com.github.devopMarkz.gerenciador_permissoes.service;

import com.github.devopMarkz.gerenciador_permissoes.dto.LoginDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.TokenDTO;
import com.github.devopMarkz.gerenciador_permissoes.dto.UsuarioDTO;
import com.github.devopMarkz.gerenciador_permissoes.mapper.UsuarioMapper;
import com.github.devopMarkz.gerenciador_permissoes.model.Empresa;
import com.github.devopMarkz.gerenciador_permissoes.model.Usuario;
import com.github.devopMarkz.gerenciador_permissoes.repository.EmpresaRepository;
import com.github.devopMarkz.gerenciador_permissoes.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;
    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;

    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper, EmpresaRepository empresaRepository, UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public TokenDTO login (LoginDTO loginDTO) {
        var passwordAuthToken = new UsernamePasswordAuthenticationToken(loginDTO.login(), loginDTO.senha());
        Authentication authentication = authenticationManager.authenticate(passwordAuthToken);
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return tokenService.obterToken(usuario);
    }

    @Transactional
    public void register (UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        String senhaEncriptada = passwordEncoder.encode(usuarioDTO.getSenha());
        usuario.setSenha(senhaEncriptada);
        usuario.setRole(Usuario.Role.ROLE_EMPRESA);

        Empresa empresa = null;

        if(usuarioDTO.getIdEmpresa() != null) {
            empresa = empresaRepository.getReferenceById(usuarioDTO.getIdEmpresa());
            usuario.setEmpresa(empresa);
        }

        usuarioRepository.save(usuario);
    }

    public Usuario obterUsuarioLogado(){
        return (Usuario) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication())
                .getPrincipal();
    }

}
