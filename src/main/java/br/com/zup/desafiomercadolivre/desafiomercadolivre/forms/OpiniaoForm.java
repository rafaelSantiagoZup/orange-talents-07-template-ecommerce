package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations.ExistsId;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Opiniao;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.ProdutoRepository;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.UsuarioRepository;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OpiniaoForm {
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    @Max(5) @Min(1)
    private Integer nota;

    public Opiniao toModel(Produto produto, Usuario usuario){
        return new Opiniao(this.titulo,this.descricao,this.nota,usuario,produto);
    }

    public OpiniaoForm() {
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public OpiniaoForm(String titulo, String descricao, Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }
}
