package br.com.zup.desafiomercadolivre.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
