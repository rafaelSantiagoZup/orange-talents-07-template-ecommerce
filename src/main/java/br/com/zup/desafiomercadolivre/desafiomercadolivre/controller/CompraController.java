package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.CompraForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.CompraRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.services.FakeEmailSender;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    private final CompraRepository compraRepository;
    private final ProdutoRepository produtoRepository;

    public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity addCompra(@PathVariable Long id, @Valid @RequestBody CompraForm compraForm, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder uriComponentsBuilder){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()) return ResponseEntity.badRequest().body("O produto da compra não existe!");
        if(produto.get().getQuantidade() < compraForm.getQuantidade())return ResponseEntity.badRequest().body("Quantidade da compra maior do que o estoque!");
        Compra compra = compraForm.toModel(produto.get(),usuario);
        produto.get().abateEstoque(compraForm.getQuantidade());
        FakeEmailSender.emailCompra(usuario,produto.get());
        try{
            compraRepository.save(compra);
            produtoRepository.save(produto.get());
            return ResponseEntity.status(HttpStatus.valueOf(302)).body(compra.redirecionamentoUrl(uriComponentsBuilder));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Ocorreu um erro na persistência das classes");
        }
    }
}
