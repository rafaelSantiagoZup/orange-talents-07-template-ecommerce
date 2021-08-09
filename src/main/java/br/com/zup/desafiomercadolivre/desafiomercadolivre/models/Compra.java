package br.com.zup.desafiomercadolivre.desafiomercadolivre.models;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.RetornoPagseguroForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.RetornoPayPalForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.interfaces.FormRequest;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra",cascade = CascadeType.MERGE)
    private List<Transacao> transacoes = new ArrayList<Transacao>();

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

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", produtoCompra=" + produtoCompra +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gateway=" + gateway +
                ", transacoes=" + transacoes +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void adicionaTransacao(@Valid FormRequest pagseguroForm) {
        Transacao novaTransacao = pagseguroForm.toTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao),"Já existe uma transação igual à essa sendo processada");
        List<Transacao> transacoesConcluidasComSucesso = transacoesConcluidasComSucesso();
        Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(),"Essa compra já foi concluída com sucesso");
        this.transacoes.add(novaTransacao);
    }

    private List<Transacao> transacoesConcluidasComSucesso() {
        List<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toList());
        Assert.isTrue(transacoesConcluidasComSucesso.size()<=1,"Mais de uma transação concluída");
        return transacoesConcluidasComSucesso;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Produto getProdutoCompra() {
        return produtoCompra;
    }

    public boolean processadaComSucesso(){
        return !transacoesConcluidasComSucesso().isEmpty();
    }

}
