package br.com.zup.desafiomercadolivre.desafiomercadolivre.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @ManyToOne
    @NotNull
    @Valid
    @JsonBackReference
    private Produto produto;
    @NotBlank
    @URL
    private String link;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getLink() {
        return link;
    }

    public Imagem(@NotBlank String nome,@NotNull @Valid Produto produto,@NotBlank @URL String link) {
        this.nome = nome;
        this.produto = produto;
        this.link = link;
    }

    @Deprecated
    public Imagem() {
    }
}
