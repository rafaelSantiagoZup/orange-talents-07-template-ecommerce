package br.com.zup.desafiomercadolivre.desafiomercadolivre.models;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public enum Gateway {

    pagseguro{
        @Override
        public String retornaUrlGateway(Compra compra, UriComponentsBuilder uriBuilder) {
            String urlRetornoPagseguro = uriBuilder.path("api/pagamento-pagseguro/{id}").buildAndExpand(compra.getId()).toString();
            return "pagseguro.com/?returnId="+compra.getId()+"&redirectUrl="+urlRetornoPagseguro;
        }
    },
    paypal{
        @Override
        String retornaUrlGateway(Compra compra, UriComponentsBuilder uriBuilder) {
            String urlRetornoPaypal = uriBuilder.path("api/pagamento-paypal/{id}").buildAndExpand(compra.getId()).toString();
            return "paypal.com/?buyerId="+compra.getId()+"&redirectUrl="+urlRetornoPaypal;
        }
    };

    abstract String retornaUrlGateway(Compra compra, UriComponentsBuilder uriComponentsBuilder);
}
