package br.com.zup.desafiomercadolivre.desafiomercadolivre.DTO;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;

public class UsuarioDTO {
    private Long id;
    private String login;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
