package br.com.zup.desafiomercadolivre.desafiomercadolivre.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false,length = 500)
    private String descricao;
    @Max(5) @Min(1)
    private Integer nota;
    @ManyToOne
    @NotNull
    private Usuario dono;
    @ManyToOne
    @NotNull
    private Produto produto;

    public Opiniao(@NotBlank String titulo,
                   @NotBlank String descricao,
                   @NotNull @Max(5) @Min(1) Integer nota,
                   @NotNull Usuario dono,
                   @NotNull Produto produto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
        this.dono = dono;
        this.produto = produto;
    }
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public Usuario getDono() {
        return dono;
    }

    public Produto getProduto() {
        return produto;
    }
    @Deprecated
    public Opiniao() {
    }

}
