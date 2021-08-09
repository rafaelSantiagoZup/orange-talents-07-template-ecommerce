package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import javax.validation.constraints.NotNull;

public class NovaCompraNF {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idComprador;

    public NovaCompraNF(Long idCompra, Long idComprador) {
        this.idCompra = idCompra;
        this.idComprador = idComprador;
    }

    @Override
    public String toString() {
        return "NovaCompraNF{" +
                "idCompra=" + idCompra +
                ", idComprador=" + idComprador +
                '}';
    }
}
