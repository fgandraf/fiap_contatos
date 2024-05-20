package com.felipegandra.contatos.service;

import com.felipegandra.contatos.dto.UsuarioCadastroDto;
import com.felipegandra.contatos.dto.UsuarioExibicaoDto;
import com.felipegandra.contatos.exception.UsuarioNaoEncontradoException;
import com.felipegandra.contatos.model.Usuario;
import com.felipegandra.contatos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    public UsuarioExibicaoDto gravar(UsuarioCadastroDto usuarioCadastroDto) {
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioCadastroDto, usuario);
        return new UsuarioExibicaoDto(repository.save(usuario));
    }


    public Page<UsuarioExibicaoDto> listarTodos(Pageable paginacao) {
        return repository
                .findAll(paginacao)
                .map(UsuarioExibicaoDto::new);
    }


    public void excluir(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            repository.delete(usuario.get());
        } else{
            throw new RuntimeException("Usuário não encontrado!");
        }
    }


    public Usuario atualizar(Usuario usuario) {
        Optional<Usuario> usuarioOptional = repository.findById(usuario.getUsuarioId());
        if (usuarioOptional.isPresent()) {
            return repository.save(usuario);
        } else{
            throw new RuntimeException("Usuário não encontrado!");
        }
    }


    public UsuarioExibicaoDto buscarPorId(Long id) {
        Optional<Usuario> usuario = repository.findById(id);
        if (usuario.isPresent()) {
            return new UsuarioExibicaoDto(usuario.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não encontrado!");
        }
    }
}