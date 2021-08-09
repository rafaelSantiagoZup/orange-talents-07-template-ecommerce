package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations.UniqueValue;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.interfaces.FormRequest;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Transacao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class RetornoPagseguroForm implements FormRequest {
    @NotBlank
    @UniqueValue(fieldName = "idTransacao",domainClass = Transacao.class)
    private String idTransacao;
    @NotNull
    private StatusPagseguro status;

    public RetornoPagseguroForm(@NotBlank String idTransacao, StatusPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    public String getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(String idTransacao) {
        this.idTransacao = idTransacao;
    }

    public StatusPagseguro getStatus() {
        return status;
    }

    public void setStatus(StatusPagseguro status) {
        this.status = status;
    }

    public RetornoPagseguroForm() {
    }

    public Transacao toTransacao(Compra compra) {
       return new Transacao(status.normaliza(),idTransacao,compra);
    }
}
