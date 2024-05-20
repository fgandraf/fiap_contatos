package com.felipegandra.contatos.controller;

import com.felipegandra.contatos.dto.ContatoCadastroDto;
import com.felipegandra.contatos.dto.ContatoExibicaoDto;
import com.felipegandra.contatos.model.Contato;
import com.felipegandra.contatos.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Api")
public class ContatoController {

    @Autowired
    private ContatoService service;

    @PostMapping("/contatos")
    @ResponseStatus(HttpStatus.CREATED)
    public ContatoExibicaoDto gravar(@RequestBody @Valid ContatoCadastroDto contatoCadastroDto) { return service.gravar(contatoCadastroDto); }

    @GetMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContatoExibicaoDto> listarTodos(Pageable paginacao) { return service.listarTodos(paginacao); }


    @DeleteMapping("/contatos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PutMapping("/contatos")
    @ResponseStatus(HttpStatus.OK)
    public Contato atualizar(@RequestBody Contato contato) {
        return service.atualizar(contato);
    }

    @GetMapping("/contatos/{nome}")
    @ResponseStatus(HttpStatus.OK)
    public ContatoExibicaoDto buscarContatoPeloNome(@PathVariable String nome) {
        return service.buscarPeloNome(nome);
    }

    @GetMapping(value = "/contatos", params = "email")
    public ContatoExibicaoDto buscarContatoPeloEmail(@RequestParam String email) { return service.buscarPeloEmail(email); }

    @GetMapping("/contatos/{dataInicial}/{dataFinal}")
    @ResponseStatus(HttpStatus.OK)
    public List<Contato> mostrarAniversariantes(@PathVariable LocalDate dataInicial, @PathVariable LocalDate dataFinal) {
        return service.mostrarAniversariantes(dataInicial, dataFinal);
    }

    @GetMapping(value = "/contatos", params = {"dataInicial", "dataFinal"})
    public List<ContatoExibicaoDto> listaAniversariantes(
            @RequestParam LocalDate dataInicial,
            @RequestParam LocalDate dataFinal
    ) {
        return service.listarAniversariantesDoPeriodo(dataInicial, dataFinal);
    }
}