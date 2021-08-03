package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations.UniqueValue;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Categoria;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.CategoriaRepository;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class CategoriaForm {
    @NotBlank @UniqueValue(fieldName = "nome",domainClass = Categoria.class)
    private String nome;
    private Long categoriaMae;

    public Categoria toModel(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(this.nome);
        if(this.categoriaMae != null){
            categoria.addCategoriaMae(categoriaRepository.findById(categoriaMae).get());
        }
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMae() {
        return categoriaMae;
    }

    public CategoriaForm(String nome, Long categoriaMae) {
        this.nome = nome;
        this.categoriaMae = categoriaMae;
    }

}
