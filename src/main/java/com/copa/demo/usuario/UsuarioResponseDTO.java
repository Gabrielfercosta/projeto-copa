package com.copa.demo.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
public record UsuarioResponseDTO( Long id, String nome, String email) {
    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}