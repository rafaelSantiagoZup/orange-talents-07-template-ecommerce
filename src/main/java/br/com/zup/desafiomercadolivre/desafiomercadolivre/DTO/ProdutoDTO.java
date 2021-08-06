package br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Opiniao;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Pergunta;
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
    private List<ImagemDTO> imagens = new ArrayList<ImagemDTO>();
    private List<PerguntaDTO> pergunta = new ArrayList<PerguntaDTO>();
    private Double nota;
    private Integer totalDeNotas;
    private List<OpiniaoDTO> opinioes = new ArrayList<OpiniaoDTO>();

    public List<CaracteristicaDTO> getCaracteristicas() {
        return caracteristicas;
    }
    public List<ImagemDTO> getImagens() {
        return imagens;
    }
    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto, List<Opiniao> opinioes, List<Pergunta> pergunta) {
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
        this.nota = calculaMediaDeNotas(opinioes);
        this.opinioes.addAll(opinioes.stream().map(opiniao1 -> new OpiniaoDTO(opiniao1)).collect(Collectors.toList()));
        this.pergunta.addAll(pergunta.stream().map(pergunta1 -> new PerguntaDTO(pergunta1)).collect(Collectors.toList()));
        this.totalDeNotas = opinioes.size();
    }

    public Integer getTotalDeNotas() {
        return totalDeNotas;
    }

    public List<OpiniaoDTO> getOpinioes() {
        return opinioes;
    }

    private Double calculaMediaDeNotas(List<Opiniao> opinioes){
        return opinioes.stream().mapToDouble(i->i.getNota()).sum()/opinioes.size();
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

    public List<PerguntaDTO> getPergunta() {
        return pergunta;
    }

    public Double getNota() {
        return nota;
    }
}
