package br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Opiniao;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;

public class OpiniaoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private Integer nota;

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

    @Deprecated
    public OpiniaoDTO() {
    }

    public OpiniaoDTO(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nota = opiniao.getNota();
    }
}
