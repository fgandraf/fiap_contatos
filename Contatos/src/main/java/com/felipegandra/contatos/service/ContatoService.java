package com.felipegandra.contatos.service;

import com.felipegandra.contatos.dto.ContatoCadastroDto;
import com.felipegandra.contatos.dto.ContatoExibicaoDto;
import com.felipegandra.contatos.exception.UsuarioNaoEncontradoException;
import com.felipegandra.contatos.model.Contato;
import com.felipegandra.contatos.repository.ContatoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;


    public ContatoExibicaoDto gravar(ContatoCadastroDto contatoCadastroDto) {
        Contato contato = new Contato();
        BeanUtils.copyProperties(contatoCadastroDto, contato);
        return new ContatoExibicaoDto(repository.save(contato));
    }

    public Contato buscarPorId(Long id) {
        Optional<Contato> contato = repository.findById(id);
        if (contato.isPresent()) {
            return contato.get();
        } else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado!");
        }
    }

    public ContatoExibicaoDto buscarPeloEmail(String email){
        Optional<Contato> contatoOptional = repository.findByEmail(email);
        if (contatoOptional.isPresent()) {
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não existe!");
        }
    }

    public ContatoExibicaoDto buscarPeloNome(String nome) {
        Optional<Contato> contatoOptional = repository.findByNome(nome);
        if (contatoOptional.isPresent()) {
            return new ContatoExibicaoDto(contatoOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Contato não encontrado!");
        }
    }

    public List<Contato> listarTodosOsContatos() {
        return repository.findAll();
    }

    public void excluir(Long id) {
        Optional<Contato> contato = repository.findById(id);
        if (contato.isPresent()) {
            repository.delete(contato.get());
        } else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public List<Contato> mostrarAniversariantes(LocalDate dataInicial, LocalDate dataFinal) {
        return repository.findByDataNascimentoBetween(dataInicial, dataFinal);
    }

    public Contato atualizar(Contato contato) {
        Optional<Contato> contatoOptional = repository.findById(contato.getId());
        if (contatoOptional.isPresent()) {
            return repository.save(contato);
        } else{
            throw new RuntimeException("Contato não encontrado!");
        }
    }

    public List<ContatoExibicaoDto> listarAniversariantesDoPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        return repository
                .listarAniversariantesDoPeriodo(dataInicial, dataFinal)
                .stream()
                .map(ContatoExibicaoDto::new)
                .toList();
    }



}
