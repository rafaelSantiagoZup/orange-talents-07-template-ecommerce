package br.com.zup.desafiomercadolivre.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {
}
