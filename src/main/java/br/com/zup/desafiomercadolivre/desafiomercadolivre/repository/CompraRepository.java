package br.com.zup.desafiomercadolivre.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra,Long> {
}
