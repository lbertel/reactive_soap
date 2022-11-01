package io.lbertel.soap.annulment.infrastructure.soap_service;

import io.lbertel.soap.annulment.domain.ports.NumberToStringConverter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class NumberToStringConverterSoapClient implements NumberToStringConverter {

    private final WebClient webClient;

    private static final String webServiceURL = "https://www.dataaccess.com/webservicesserver/NumberConversion.wso";
    private static final String xmlRequest = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
            "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
            "  <soap:Body>" +
            "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">" +
            "      <ubiNum>100</ubiNum>" +
            "    </NumberToWords>" +
            "  </soap:Body>" +
            "</soap:Envelope>";

    public NumberToStringConverterSoapClient(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }

    @Override
    public Mono<String> converter() {
        return webClient.post()
                        .uri(webServiceURL)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_XML_VALUE)
                        .bodyValue(xmlRequest)
                        .exchangeToMono(response -> {
                            if(response.statusCode().equals(HttpStatus.OK)){
                                return response.bodyToMono(String.class);
                            }
                            if(response.statusCode().is4xxClientError()){
                                return Mono.just("Error response");
                            }
                            return response.createException().flatMap(Mono::error);
                        }
                );

    }

}
