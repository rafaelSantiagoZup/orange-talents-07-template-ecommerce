package br.com.zup.desafiomercadolivre.desafiomercadolivre.interfaces;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Transacao;

public interface FormRequest {
    public Transacao toTransacao(Compra compra);
}
