package br.com.zup.desafiomercadolivre.desafiomercadolivre.services;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Pergunta;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;

public class FakeEmailSender {
    public static void emailSender(Usuario remetente, Usuario destinatario, Pergunta perguta){
        System.out.println("De: "+remetente.getLogin());
        System.out.println("Para: "+destinatario.getLogin());
        System.out.println("Titulo: "+perguta.getTitulo());
    }
}
