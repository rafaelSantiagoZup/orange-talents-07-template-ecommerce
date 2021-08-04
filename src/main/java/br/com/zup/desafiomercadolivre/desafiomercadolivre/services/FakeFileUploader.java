package br.com.zup.desafiomercadolivre.desafiomercadolivre.services;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.forms.ImagemForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FakeFileUploader {
    public static List<ImagemForm> uploadFile(List<MultipartFile> multipartFile){
        List<ImagemForm> listaRetorno = new ArrayList<ImagemForm>();
        multipartFile.stream().forEach(imagem-> {
                    String nome = imagem.getOriginalFilename();
                    listaRetorno.add(new ImagemForm(nome,URI.create("http://desafioecommercezup.com.br/"+nome.trim().replace(" ", "%20")).toString()));
                });
        return listaRetorno;
    }
}
