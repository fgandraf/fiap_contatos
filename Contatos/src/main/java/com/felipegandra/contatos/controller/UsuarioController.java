package com.felipegandra.contatos.controller;

import com.felipegandra.contatos.dto.UsuarioCadastroDto;
import com.felipegandra.contatos.dto.UsuarioExibicaoDto;
import com.felipegandra.contatos.model.Usuario;
import com.felipegandra.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto gravar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        return service.gravar(usuarioCadastroDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UsuarioExibicaoDto> listarTodos(Pageable paginacao) {
        return service.listarTodos(paginacao);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Usuario atualizar(@RequestBody Usuario usuario) {
        return service.atualizar(usuario);
    }


    @GetMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioExibicaoDto buscarPorId(@PathVariable Long usuarioId) {
        return service.buscarPorId(usuarioId);
    }
}