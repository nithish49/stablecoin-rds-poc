package com.example.stablecoin.service;

import org.springframework.stereotype.Service;

@Service
public class VdpsIntegrationService {

    private final SettlementService settlement;

    public VdpsIntegrationService(SettlementService settlement) {
        this.settlement = settlement;
    }

    public void accept(String raw){
        VdpsIsoParser parser = new VdpsIsoParser();
        var tx = parser.parse(raw);
        settlement.settle(tx);
    }
}
