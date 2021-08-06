package br.com.zup.desafiomercadolivre.desafiomercadolivre.services;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Pergunta;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;

public class FakeEmailSender {
    public static void emailSender(Usuario remetente, Usuario destinatario, Pergunta perguta){
        System.out.println("De: "+remetente.getLogin());
        System.out.println("Para: "+destinatario.getLogin());
        System.out.println("Titulo: "+perguta.getTitulo());
    }
    public static void emailCompra(Usuario comprador,Produto produto){
        System.out.println("De: contato@mercadolivre.com.br");
        System.out.println("Para: "+produto.getDono().getLogin());
        System.out.println("Você possui uma nova compra do produto: "+produto.getNome());
        System.out.println("Comprador: "+comprador.getLogin());
    }
}
