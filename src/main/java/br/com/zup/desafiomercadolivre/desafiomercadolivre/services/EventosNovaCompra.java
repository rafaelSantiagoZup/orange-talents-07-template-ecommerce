package br.com.zup.desafiomercadolivre.desafiomercadolivre.services;

import br.com.zup.desafiomercadolivre.desafiomercadolivre.interfaces.Processamento;
import br.com.zup.desafiomercadolivre.desafiomercadolivre.models.Compra;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EventosNovaCompra {
    private final Set<Processamento> eventosCompraSucesso;

    public EventosNovaCompra(Set<Processamento> eventosCompraSucesso) {
        this.eventosCompraSucesso = eventosCompraSucesso;
    }
    public void processa(Compra compra) {
        if(compra.processadaComSucesso()){
            eventosCompraSucesso.forEach(evento->evento.processa(compra));
        }
    }
}
