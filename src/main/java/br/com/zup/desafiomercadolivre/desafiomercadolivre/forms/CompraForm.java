package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Gateway;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraForm {
    @Positive
    private Integer quantidade;
    @NotNull
    private Gateway gateway;

    public Compra toModel(Produto produto, Usuario usuario){
        return new Compra(produto,this.quantidade,usuario,this.gateway);
    }
    @Deprecated
    public CompraForm() {
    }

    public CompraForm(Integer quantidade, Gateway gateway) {
        this.quantidade = quantidade;
        this.gateway = gateway;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Gateway getGateway() {
        return gateway;
    }
}
