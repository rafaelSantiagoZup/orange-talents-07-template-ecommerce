package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.ProdutoDTO;
import static br.com.zup.desafiomercadolivre.desafiomercadolivre.services.FakeFileUploader.uploadFile;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.ProdutoForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.UploadFileForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.CategoriaRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public ProdutoController(ProdutoRepository produtoRepository,
                             CategoriaRepository categoriaRepository,
                             UsuarioRepository usuarioRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> addProduto(@RequestBody @Valid ProdutoForm produtoForm, @AuthenticationPrincipal Long usuarioId ){
        System.out.println(usuarioId);
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
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
    public ResponseEntity<?> addImagemProduto(@Valid UploadFileForm imagens, @PathVariable Long id, @AuthenticationPrincipal Usuario usuario ){
        Produto produto = produtoRepository.findById(id).get();
        System.out.println(produto.pertenceAoUsuario(usuario));
        if(produto.pertenceAoUsuario(usuario)){
            produto.setImagens(uploadFile(imagens.getFiles()));
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
