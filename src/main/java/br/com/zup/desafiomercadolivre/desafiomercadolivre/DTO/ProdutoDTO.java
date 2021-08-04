package br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Imagem;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private List<Imagem> imagens;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        if(!produto.getImagens().isEmpty()){
            this.imagens = new ArrayList<Imagem>();
            produto.getImagens().stream()
                    .map(imagem -> this.imagens.add(imagem)).collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
