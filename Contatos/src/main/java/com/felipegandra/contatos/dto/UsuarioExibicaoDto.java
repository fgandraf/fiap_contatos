package com.felipegandra.contatos.dto;

import com.felipegandra.contatos.model.Usuario;

public record UsuarioExibicaoDto(
        Long usuarioId,
        String nome,
        String email
) {

    public UsuarioExibicaoDto(Usuario usuario) {
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
