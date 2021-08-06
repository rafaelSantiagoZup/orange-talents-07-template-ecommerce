package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.OpiniaoDTO;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.configuration.security.services.TokenService;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.OpiniaoForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Opiniao;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.OpiniaoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.stream.Collectors;

import static br.com.zup.desafiomercadolivre.desafiomercadolivre.common.utils.TokenUtils.tokenSubstring;

@RestController
public class OpiniaoController {
    private final OpiniaoRepository opiniaoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public OpiniaoController(OpiniaoRepository opiniaoRepository,
                             ProdutoRepository produtoRepository,
                             UsuarioRepository usuarioRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }
    @PostMapping("api/produto/opiniao/{id}")
    @Transactional
    public ResponseEntity addOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoForm opiniaoForm, @AuthenticationPrincipal Usuario usuario){
        Produto produto = produtoRepository.findById(id).get();
        if(produto.pertenceAoUsuario(usuario)){
            Opiniao opiniao = opiniaoForm.toModel(produto,usuario);
            try{
                opiniaoRepository.save(opiniao);
                return ResponseEntity.ok(new OpiniaoDTO(opiniao));
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
