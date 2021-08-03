package br.com.zup.desafiomercadolivre.desafiomercadolivre.repository;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
