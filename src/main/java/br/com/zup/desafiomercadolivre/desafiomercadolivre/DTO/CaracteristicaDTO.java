package br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Caracteristicas;

public class CaracteristicaDTO {
    private String nome;
    private String descricao;

    public CaracteristicaDTO(Caracteristicas carac) {
        this.nome = carac.getNome();
        this.descricao = carac.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public CaracteristicaDTO() {
    }
}
