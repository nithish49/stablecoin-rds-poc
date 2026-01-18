package com.example.stablecoin.integration;
import com.example.stablecoin.model.CardTransaction;
import java.math.BigDecimal;
public class VdpsIsoParser {
    public CardTransaction parse(String input){
        CardTransaction tx = new CardTransaction();
        tx.setAmount(extractAmount(input));
        tx.setCardType(extract(input, "[Acquirer BIN :", "]"));
        tx.setTransactionType(extract(input, "[MTI :", "]"));
        tx.setMerchantId(extract(input, "[Merchant Identifier :", "]"));
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
}
