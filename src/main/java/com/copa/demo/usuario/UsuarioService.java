package com.copa.demo.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioResponseDTO> getAllUsuarios() {
        return repository.findAll()
                .stream()
                .map(UsuarioResponseDTO::new)
                .toList();
    }

    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO data) {
        Usuario usuario = new Usuario(data);
        Usuario saved = repository.save(usuario);
        return new UsuarioResponseDTO(saved);
    }

    public UsuarioResponseDTO updateUsuario(Long id, UsuarioRequestDTO data) {
        Usuario usuario = repository.findById(id).orElseThrow();

        usuario.setNome(data.nome());
        usuario.setEmail(data.email());
        usuario.setSenha(data.senha());

        Usuario usuarioUpdated = repository.save(usuario);
        return new UsuarioResponseDTO(usuarioUpdated);
    }

    public void deleteUsuario(Long id){
        repository.deleteById(id);
    }

    public UsuarioResponseDTO login(UsuarioRequestDTO data) {
        Usuario usuario = repository.findByEmail(data.email());
        if (usuario == null || !usuario.getSenha().equals(data.senha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha incorretos");
        }
        return new UsuarioResponseDTO(usuario);
    }

}