package br.com.zup.desafiomercadolivre.desafiomercadolivre.services;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.interfaces.Processamento;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import org.springframework.stereotype.Service;

@Service
public class EmailSucesso implements Processamento {

    @Override
    public void processa(Compra compra) {
        FakeEmailSender.emailCompra(compra.getComprador(), compra.getProdutoCompra());
    }
}
