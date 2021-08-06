package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.PerguntaForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Pergunta;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.PerguntaRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.services.FakeEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@RestController
public class PerguntaController {

    private final ProdutoRepository produtoRepository;
    private final PerguntaRepository perguntaRepository;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
    }


    @PostMapping("/api/produto/pergunta/{id}")
    @Transactional
    private ResponseEntity addPergunta(@PathVariable Long id, @RequestBody PerguntaForm pergunta, @AuthenticationPrincipal Usuario remetente){
        Produto produto = produtoRepository.findById(id).get();
        Pergunta perguntaResp = pergunta.toModel(remetente,produto);
        try{
            perguntaRepository.save(perguntaResp);
            FakeEmailSender.emailSender(remetente, produto.getDono(),perguntaResp);
            return  ResponseEntity.ok("Pergunta de id "+perguntaResp.getId()+" enviada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
