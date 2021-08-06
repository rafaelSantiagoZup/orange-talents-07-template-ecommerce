package br.com.zup.desafiomercadolivre.desafiomercadolivre.forms;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class UploadFileForm {
    @Size(min = 1)
    @NotNull(message = "Selecione pelo menos uma imagem para enviar!")
    private List<MultipartFile> file = new ArrayList<MultipartFile>();

    public void setFile(@NotNull @Size(min = 1) List<MultipartFile> file) {
        Assert.isTrue(!file.isEmpty(),"Algo de errado aconteceu que o valor não veio");
        this.file = file;
    }

    public List<MultipartFile> getFiles() {
        return file;
    }

    @Deprecated
    public UploadFileForm() {
    }
}
