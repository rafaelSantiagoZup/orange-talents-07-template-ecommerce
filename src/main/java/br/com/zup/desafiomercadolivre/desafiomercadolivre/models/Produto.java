package br.com.zup.desafiomercadolivre.desafiomercadolivre.models;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.CaracteristicasForm;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull @Positive
    private BigDecimal valor;
    @NotNull @Positive
    private Integer quantidade;
    @OneToMany(mappedBy = "produto",cascade = CascadeType.ALL)
    private Set<Caracteristicas> caracteristicas = new HashSet<Caracteristicas>();
    @NotBlank @Length(max = 1000)
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario dono;
    private LocalDateTime instanteCadastro = LocalDateTime.now();

    public Produto(
            @NotBlank String nome,
            @NotBlank @Positive BigDecimal valor,
            @NotBlank @Positive Integer quantidade,
            @Size(min = 3) Collection<CaracteristicasForm> caracteristicas,
            @NotBlank @Length(max = 1000) String descricao,
            Categoria categoria,
            @NotNull @Valid Usuario dono) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas.stream()
                .map(caracteristicasForms->caracteristicasForms.toModel(this))
                .collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.dono = dono;
    }

    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<Caracteristicas> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDono() {
        return dono;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }
}