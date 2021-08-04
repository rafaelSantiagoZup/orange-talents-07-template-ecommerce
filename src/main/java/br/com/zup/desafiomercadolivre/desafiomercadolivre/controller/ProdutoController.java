package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.ProdutoDTO;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.utils.TokenUtils;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.configuration.security.services.TokenService;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.ProdutoForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.CategoriaRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final TokenService tokenService;
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public ProdutoController(TokenService tokenService,
                             ProdutoRepository produtoRepository,
                             CategoriaRepository categoriaRepository,
                             UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> addProduto(@RequestBody @Valid ProdutoForm produtoForm, @RequestHeader("Authorization") String token ){
        Long userId = tokenService.getIdUsuario(TokenUtils.tokenSubstring(token));
        Usuario usuario = usuarioRepository.findById(userId).get();
        Produto produto = produtoForm.toModel(usuario,categoriaRepository);
        try{
            produtoRepository.save(produto);
            return ResponseEntity.ok(new ProdutoDTO(produto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }


    }
}
