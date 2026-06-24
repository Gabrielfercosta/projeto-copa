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
        if (repository.findByEmail(data.email()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado");
        }
        if (data.senha().length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha deve ter no mínimo 6 caracteres");
        }
        if (!data.email().contains("@") || !data.email().contains(".")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido");
        }
        if (data.nome() == null || data.nome().trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome é obrigatório");
        }
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