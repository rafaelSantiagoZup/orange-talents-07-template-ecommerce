package br.com.zup.desafiomercadolivre.desafiomercadolivre.services;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.interfaces.Processamento;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements Processamento {
    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> request = Map.of("idCompra",compra.getId(),"idVendedor",compra.getProdutoCompra().getDono().getId());
        try {
            restTemplate.postForEntity("http://localhost:8080/api/ranking",request,String.class);
        }catch (RestClientException e){
            throw e;
        }
    }
}
