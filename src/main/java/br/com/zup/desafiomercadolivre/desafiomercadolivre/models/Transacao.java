package br.com.zup.desafiomercadolivre.desafiomercadolivre.models;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations.UniqueValue;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Compra compra;
    @NotNull
    private StatusTransacao status;
    @NotBlank
    @Column(unique = true)
    private String idTransacao;
    @NotNull
    private LocalDateTime instante;

    public Transacao(@NotNull StatusTransacao status, @NotBlank String idTransacao
    ,@NotNull @Valid Compra compra) {
        this.status = status;
        this.idTransacao = idTransacao;
        this.instante = LocalDateTime.now();
        this.compra = compra;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", idTransacao='" + idTransacao + '\'' +
                ", instante=" + instante +
                '}';
    }

    @Deprecated
    public Transacao() {
    }

    public boolean concluidaComSucesso(){
        return this.status.equals(StatusTransacao.sucesso);
    }
}
