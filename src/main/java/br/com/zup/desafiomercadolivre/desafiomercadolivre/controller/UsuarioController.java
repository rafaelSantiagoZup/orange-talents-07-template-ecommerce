package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.UsuarioDTO;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.UsuarioForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> addUsuario(@Valid @RequestBody UsuarioForm usuarioForm){
        Usuario usuario = usuarioForm.toModel();
        try{
            usuarioRepository.save(usuario);
            return ResponseEntity.ok().body(new UsuarioDTO(usuario));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
