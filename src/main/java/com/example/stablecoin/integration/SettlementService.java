package com.example.stablecoin.integration;
import com.example.stablecoin.model.CardTransaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
@Service
public class SettlementService {
    private final StablecoinClient client;
    private final SettlementLedger ledger;
    public SettlementService(StablecoinClient client, SettlementLedger ledger){
        this.client = client; this.ledger = ledger;
    }
    @Async
    public void settleAsync(CardTransaction tx){
        try{
            String res = client.process(tx);
            ledger.record(tx,res);
        }catch(Exception ex){
            ledger.recordFailure(tx,ex.getMessage());
        }
    }
}
