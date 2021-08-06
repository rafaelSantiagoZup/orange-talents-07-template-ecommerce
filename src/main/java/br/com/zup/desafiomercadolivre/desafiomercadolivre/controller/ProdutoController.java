package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.ProdutoDTO;
import static br.com.zup.desafiomercadolivre.desafiomercadolivre.services.FakeFileUploader.uploadFile;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.ProdutoForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.UploadFileForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Opiniao;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Pergunta;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PerguntaRepository perguntaRepository;
    private final OpiniaoRepository opiniaoRepository;

    public ProdutoController(ProdutoRepository produtoRepository,
                             CategoriaRepository categoriaRepository,
                             UsuarioRepository usuarioRepository,
                             PerguntaRepository perguntaRepository,
                             OpiniaoRepository opiniaoRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.perguntaRepository = perguntaRepository;
        this.opiniaoRepository = opiniaoRepository;
    }


    @PostMapping
    @Transactional
    public ResponseEntity<?> addProduto(@RequestBody @Valid ProdutoForm produtoForm, @AuthenticationPrincipal Usuario usuario ){
        Produto produto = produtoForm.toModel(usuario,categoriaRepository);
        try{
            produtoRepository.save(produto);
            return ResponseEntity.ok("produto de id:"+produto.getId()+" salvo com sucesso");
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/imagem/{id}")
    @Transactional
    public ResponseEntity<?> addImagemProduto(@Valid UploadFileForm imagens, @PathVariable Long id, @AuthenticationPrincipal Usuario usuario ){
        Produto produto = produtoRepository.findById(id).get();
        if(produto.pertenceAoUsuario(usuario)){
            produto.setImagens(uploadFile(imagens.getFiles()));
            try{
                produtoRepository.save(produto);
                return ResponseEntity.ok("imagens salvas com sucesso");
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    @GetMapping("/detalhes/{id}")
    public ResponseEntity detalhesProduto(@PathVariable Long id){
        Produto produto = produtoRepository.getById(id);
        List<Opiniao> opinioes = opiniaoRepository.findAllByProduto(produto);
        List<Pergunta> perguntas = perguntaRepository.findAllByProduto(produto);
        ProdutoDTO produtoResposta = new ProdutoDTO(produto,opinioes,perguntas);
        return ResponseEntity.ok(produtoResposta);
    }
}
