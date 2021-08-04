package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Imagem;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.zup.desafiomercadolivre.desafiomercadolivre.services.FakeFileUploader.uploadFile;

public class ImagemForm {
    @NotBlank
    private String nome;
    @NotBlank
    @URL
    private String link;

        public Imagem toModel(Produto produto){
        return new Imagem(this.nome,produto,this.link);
    }
    public ImagemForm(String nome, String link) {
        this.nome = nome;
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public String getLink() {
        return link;
    }
}
