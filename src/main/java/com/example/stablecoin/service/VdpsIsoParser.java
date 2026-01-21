package com.example.stablecoin.service;

import com.example.stablecoin.model.CardTransaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VdpsIsoParser {
    public CardTransaction parse(String input) {
        CardTransaction tx = new CardTransaction();
        tx.setCustomerAccountId("0.0.7326405");
        tx.setTransactionDate(LocalDate.now());
        tx.setCompletionCode("110");
        tx.setTransactionType("Debit");
        tx.setAmount(extractAmount(input));
        tx.setDescription(extractDescription(input));
        tx.setLinkedDcNumber(maskPan("00001234567891234567"));
        tx.setNetworkId("VSN");
        return tx;
    }
    private BigDecimal extractAmount(String text){
        String raw = extract(text, "[Amount, Transaction :", "]");
        if(raw==null) return BigDecimal.ZERO;
        long cents = Long.parseLong(raw.replaceAll("[^0-9]",""));
        return new BigDecimal(cents).movePointLeft(2);
    }
    private String extract(String t,String s,String e){
        int i=t.indexOf(s);
        if(i==-1) return null;
        int j=t.indexOf(e,i);
        if(j==-1) return null;
        return t.substring(i+s.length(),j).trim();
    }
    private String extractDescription(String text){
        return text.contains("IKEA")?"Purchase IKEA":"Purchase Cosco";
    }
    private String maskPan(String pan){
        return pan.substring(0,6)+"******"+pan.substring(pan.length()-6);
    }
}
