package com.example.stablecoin.service;

import com.example.stablecoin.model.CardTransaction;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SettlementService {

    private final StablecoinRestClient rest;
    private final CustomerLedgerRepo ledgerRepo;
    private final SwitchSettlementRepo switchRepo;

    public SettlementService(StablecoinRestClient rest, CustomerLedgerRepo ledgerRepo, SwitchSettlementRepo switchRepo){
        this.rest = rest;
        this.ledgerRepo = ledgerRepo;
        this.switchRepo = switchRepo;
    }

    @Async
    public void settle(CardTransaction tx){
        rest.burn(tx).subscribe(resp -> {
            CustomerLedger c = new CustomerLedger();
            c.customerAccountId = tx.getCustomerAccountId();
            c.transactionDate = tx.getTransactionDate();
            c.completionCode = tx.getCompletionCode();
            c.transactionType = tx.getTransactionType();
            c.amount = tx.getAmount();
            c.description = tx.getDescription();
            c.linkedDcNumber = tx.getLinkedDcNumber();
            c.networkId = tx.getNetworkId();
            ledgerRepo.save(c);

            SwitchSettlement s = new SwitchSettlement();
            s.switchAccountGl = tx.getCustomerAccountId();
            s.effectiveDate = LocalDate.now();
            s.transactionType = "Credit";
            s.amount = tx.getAmount();
            s.description = "Net Settlement";
            switchRepo.save(s);
        });
    }
}
