package br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Imagem;

public class ImagemDTO {
    private String nome;
    private String link;

    public ImagemDTO(Imagem imagem) {
        this.nome = imagem.getNome();
        this.link = imagem.getLink();
    }

    public ImagemDTO() {
    }

    public String getNome() {
        return nome;
    }

    public String getLink() {
        return link;
    }
}
