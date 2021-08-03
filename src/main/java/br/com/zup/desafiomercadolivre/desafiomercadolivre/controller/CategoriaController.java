package br.com.zup.desafiomercadolivre.desafiomercadolivre.controller;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.CategoriaDTO;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO.UsuarioDTO;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.CategoriaForm;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Categoria;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<?> addCategoria(@Valid @RequestBody CategoriaForm categoriaForm){
        Categoria categoria = categoriaForm.toModel(categoriaRepository);
        try{
            categoriaRepository.save(categoria);
            return ResponseEntity.ok().body(new CategoriaDTO(categoria));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
