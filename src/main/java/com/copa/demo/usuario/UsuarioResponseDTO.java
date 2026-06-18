package com.copa.demo.usuario;

public record UsuarioResponseDTO(Long id, String nome, String email) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
