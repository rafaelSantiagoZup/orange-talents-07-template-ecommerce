package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations.ExistsId;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations.UniqueValue;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Categoria;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoForm {
    @UniqueValue(domainClass = Produto.class,fieldName = "nome")
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull @Positive
    private Integer quantidade;
    @Size(min = 3)
    @Valid
    private List<CaracteristicasForm> caracteristicas = new ArrayList<CaracteristicasForm>();
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id",message = "Não existe categoria com esse ID!")
    private Long categoriaId;

    public Produto toModel(Usuario usuario, CategoriaRepository categoriaRepository){
        Categoria categoria = categoriaRepository.findById(this.categoriaId).get();
        return new Produto(this.nome,
                this.valor,
                this.quantidade,
                this.caracteristicas,
                this.descricao,
                categoria,
                usuario);
    }

    public ProdutoForm(String nome, BigDecimal valor, Integer quantidade, List<CaracteristicasForm> caracteristicas, String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas.addAll(caracteristicas);
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public List<CaracteristicasForm> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<CaracteristicasForm> caracteristicas) {
        this.caracteristicas = caracteristicas;
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

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}
