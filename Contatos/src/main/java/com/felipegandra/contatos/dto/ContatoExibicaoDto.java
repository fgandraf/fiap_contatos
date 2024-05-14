package com.felipegandra.contatos.dto;

import com.felipegandra.contatos.model.Contato;

import java.time.LocalDate;

public record ContatoExibicaoDto(
        Long id,
        String nome,
        String email,
        LocalDate dataNascimento
) {
    public ContatoExibicaoDto(Contato contato) {
        this(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getDataNascimento()
        );
    }
}
