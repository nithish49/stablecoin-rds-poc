package com.example.stablecoin.integration;

import com.example.stablecoin.model.CardTransaction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;

@Service
public class StablecoinClient {

    private final WebClient client = WebClient.create("http://localhost:8080");

    // TODO: you can later map merchant → Hedera account dynamically
    private static final String ACCOUNT = "0.0.7326405";

    public String process(CardTransaction tx) {

        // Extract amount in $ and convert to token units (2 decimals → cents)
        BigDecimal amount = tx.getAmount();             // e.g. 2.00
        long units = amount.movePointRight(2).longValue(); // → 200

        // 1. Burn tokens
        String burnResp = client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/token/burn")
                        .queryParam("account", ACCOUNT)
                        .queryParam("amount", units)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // 2. Check remaining balance
        String balance = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/token/balance")
                        .queryParam("account", ACCOUNT)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return "BURN=" + burnResp + " BALANCE=" + balance;
    }
}
