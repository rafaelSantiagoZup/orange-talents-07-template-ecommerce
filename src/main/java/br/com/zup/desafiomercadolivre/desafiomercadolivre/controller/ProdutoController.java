package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.ProdutoDTO;
import static br.com.zup.desafiomercadolivre.desafiomercadolivre.common.utils.TokenUtils.*;
import static br.com.zup.desafiomercadolivre.desafiomercadolivre.services.FakeFileUploader.uploadFile;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.configuration.security.services.TokenService;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.ImagemForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.ProdutoForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.CategoriaRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.UsuarioRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

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
    @Transactional
    public ResponseEntity<?> addProduto(@RequestBody @Valid ProdutoForm produtoForm, @RequestHeader("Authorization") String token ){
        Long userId = tokenService.getIdUsuario(tokenSubstring(token));
        Usuario usuario = usuarioRepository.findById(userId).get();
        Produto produto = produtoForm.toModel(usuario,categoriaRepository);
        try{
            produtoRepository.save(produto);
            return ResponseEntity.ok(new ProdutoDTO(produto));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/imagem/{id}")
    @Transactional
    public ResponseEntity<?> addImagemProduto(@RequestParam(value = "file",required = true) @Valid @NotNull @Length(min = 1) List<MultipartFile> imagens, @PathVariable Long id, @RequestHeader("Authorization") String token ){
        Long userId = tokenService.getIdUsuario(tokenSubstring(token));
        Usuario usuario = usuarioRepository.findById(userId).get();
        Produto produto = produtoRepository.findById(id).get();
        if(produto.pertenceAoUsuario(usuario)){
            produto.setImagens(uploadFile(imagens));
            try{
                produtoRepository.save(produto);
                return ResponseEntity.ok(new ProdutoDTO(produto));
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
