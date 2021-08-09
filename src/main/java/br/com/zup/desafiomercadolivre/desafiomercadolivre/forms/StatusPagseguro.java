package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.StatusTransacao;

public enum StatusPagseguro {
    SUCESSO,ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)){
            return StatusTransacao.sucesso;
        }else
            return StatusTransacao.erro;
    }
}