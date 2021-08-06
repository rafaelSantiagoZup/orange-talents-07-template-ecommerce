package br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private List<CaracteristicaDTO> caracteristicas = new ArrayList<CaracteristicaDTO>();
    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }

    private List<ImagemDTO> imagens = new ArrayList<ImagemDTO>();

    public List<ImagemDTO> getImagens() {
        return imagens;
    }

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        if(!produto.getImagens().isEmpty()){
            this.imagens.addAll(produto
                    .getImagens()
                    .stream().map(imagem -> new ImagemDTO(imagem))
                    .collect(Collectors.toList()));
        }
        this.caracteristicas.addAll(produto
                .getCaracteristicas()
                .stream()
                .map(caracteristica-> new CaracteristicaDTO(caracteristica))
                .collect(Collectors.toList()));
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
