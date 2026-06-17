package com.copa.demo.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public record UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String email;

    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}