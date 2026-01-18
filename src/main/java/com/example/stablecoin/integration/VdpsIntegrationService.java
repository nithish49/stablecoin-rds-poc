package com.example.stablecoin.integration;
import com.example.stablecoin.model.CardTransaction;
import org.springframework.stereotype.Service;
@Service
public class VdpsIntegrationService {
    private final VdpsIsoParser parser = new VdpsIsoParser();
    private final SettlementService settlement;
    private final RdsService rds;
    public VdpsIntegrationService(SettlementService settlement, RdsService rds){
        this.settlement = settlement;
        this.rds = rds;
    }
    public void accept(String raw){
        CardTransaction tx = parser.parse(raw);
        if(rds.requiresStablecoin(tx)){
            settlement.settleAsync(tx);
        }
    }
}
