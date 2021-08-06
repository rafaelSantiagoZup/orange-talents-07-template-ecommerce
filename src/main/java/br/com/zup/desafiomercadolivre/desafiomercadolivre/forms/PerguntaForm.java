package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Pergunta;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaForm {
    @NotBlank
    private String titulo;

    public Pergunta toModel(Usuario remetente, Produto produto){
        return new Pergunta(this.titulo,remetente,produto);
    }

    public String getTitulo() {
        return titulo;
    }

    public PerguntaForm(String titulo) {
        this.titulo = titulo;
    }

    @Deprecated
    public PerguntaForm() {
    }
}
