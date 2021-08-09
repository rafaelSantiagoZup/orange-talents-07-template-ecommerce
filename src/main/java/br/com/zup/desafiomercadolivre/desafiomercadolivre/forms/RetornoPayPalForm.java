package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.interfaces.FormRequest;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.StatusTransacao;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPayPalForm implements FormRequest {
    @NotBlank
    private String idTransacao;
    @NotNull @Max(1) @Min(0)
    private Integer status;

    @Deprecated
    public RetornoPayPalForm() {
    }
    public RetornoPayPalForm(String idTransacao, Integer status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPayPalForm{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao toTransacao(Compra compra) {
        StatusTransacao statusTransacao = this.status==0? StatusTransacao.erro:StatusTransacao.sucesso;
        return new Transacao(statusTransacao,this.idTransacao,compra);
    }
}
