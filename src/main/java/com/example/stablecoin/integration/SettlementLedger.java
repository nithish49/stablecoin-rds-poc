package com.example.stablecoin.integration;
import com.example.stablecoin.model.CardTransaction;
import org.springframework.stereotype.Service;
@Service
public class SettlementLedger {
    public void record(CardTransaction tx,String result){
        System.out.println("SETTLED: "+tx.getAmount()+" => "+result);
    }
    public void recordFailure(CardTransaction tx,String reason){
        System.out.println("FAILED: "+reason);
    }
}
