package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.common.annotations.UniqueValue;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Usuario;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioForm {
    @NotBlank
    @Email
    private String login;
    @Length(min = 6)
    @NotBlank
    private String senha;

    public UsuarioForm(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login,geraHashSenha());
    }

    private String geraHashSenha(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(this.senha);
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
