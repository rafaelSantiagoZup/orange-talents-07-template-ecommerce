package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import javax.validation.constraints.NotNull;

public class NovaCompraRanking {
    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public NovaCompraRanking(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor= idVendedor;
    }

    @Override
    public String toString() {
        return "NovaCompraRanking{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
