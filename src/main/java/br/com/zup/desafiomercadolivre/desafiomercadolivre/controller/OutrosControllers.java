package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.NovaCompraNF;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.NovaCompraRanking;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class OutrosControllers {

    @PostMapping("/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNF request)throws InterruptedException{
        System.out.println("criando notas para "+request);
        Thread.sleep(150);
    }
    @PostMapping("/ranking")
    public void ranking(@Valid @RequestBody NovaCompraRanking request)throws InterruptedException{
        System.out.println("criando Ranking para "+request);
        Thread.sleep(150);
    }
}
