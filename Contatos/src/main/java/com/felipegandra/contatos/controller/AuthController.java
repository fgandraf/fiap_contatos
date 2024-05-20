package com.felipegandra.contatos.controller;


import com.felipegandra.contatos.config.security.TokenService;
import com.felipegandra.contatos.dto.LoginDto;
import com.felipegandra.contatos.dto.TokenDto;
import com.felipegandra.contatos.dto.UsuarioCadastroDto;
import com.felipegandra.contatos.dto.UsuarioExibicaoDto;
import com.felipegandra.contatos.model.Usuario;
import com.felipegandra.contatos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDto loginDto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(
                loginDto.email(),
                loginDto.senha()
        );

        var auth = authenticationManager.authenticate(usernamePassword);
        var token = tokenService.gerarToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new TokenDto(token));
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioExibicaoDto registrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
        return usuarioService.gravar(usuarioCadastroDto);
    }

}
