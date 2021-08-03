package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.TokenDTO;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.configuration.security.services.TokenService;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.AutenticacaoForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.UsuarioForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/autenticacao")
public class AutenticacaoController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }


    @PostMapping
    public ResponseEntity<TokenDTO> autenticarUsuario(@RequestBody @Valid AutenticacaoForm autenticacaoForm){
        UsernamePasswordAuthenticationToken dados = autenticacaoForm.toLogin();
        try{
            Authentication authentication =authManager.authenticate(dados);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token,"Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
