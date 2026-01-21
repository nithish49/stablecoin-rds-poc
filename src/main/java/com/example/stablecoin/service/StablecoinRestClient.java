package com.example.stablecoin.service;

import com.example.stablecoin.model.CardTransaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class StablecoinRestClient {

    private final WebClient client;

    public StablecoinRestClient(@Value("${stablecoin.rest.url}") String baseUrl) {
        this.client = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<String> burn(CardTransaction tx){

        // Extract amount in $ and convert to token units (2 decimals → cents)
        BigDecimal amount = tx.getAmount();             // e.g. 2.00
        long units = amount.movePointRight(2).longValue(); // → 200
        return client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/burn")
                        .queryParam("account", tx.getCustomerAccountId())
                        .queryParam("amount", units)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
