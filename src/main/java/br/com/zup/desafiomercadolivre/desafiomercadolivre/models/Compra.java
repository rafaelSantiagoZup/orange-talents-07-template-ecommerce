package br.com.zup.desafiomercadolivre.desafiomercadolivre.models;

import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    @Valid
    private Produto produtoCompra;
    @Positive
    private Integer quantidade;
    @ManyToOne
    @NotNull
    @Valid
    private Usuario comprador;
    @Enumerated
    @NotNull
    private Gateway gateway;

    public Compra(@NotNull @Valid Produto produtoCompra,
                  @Positive int quantidade,
                  @NotNull Usuario comprador,
                  Gateway gateway) {
        this.produtoCompra = produtoCompra;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
    }

    public String redirecionamentoUrl(UriComponentsBuilder uriComponentsBuilder){
        return this.gateway.retornaUrlGateway(this,uriComponentsBuilder);
    }

    @Deprecated
    public Compra() {
    }

    public Long getId() {
        return id;
    }

}
