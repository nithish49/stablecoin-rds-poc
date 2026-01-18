package com.example.stablecoin.model;
import java.math.BigDecimal;
public class CardTransaction {
    private BigDecimal amount;
    private String cardType;
    private String transactionType;
    private String merchantId;
    public BigDecimal getAmount(){ return amount; }
    public void setAmount(BigDecimal a){ this.amount=a; }
    public String getCardType(){ return cardType; }
    public void setCardType(String c){ this.cardType=c; }
    public String getTransactionType(){ return transactionType; }
    public void setTransactionType(String t){ this.transactionType=t; }
    public String getMerchantId(){ return merchantId; }
    public void setMerchantId(String m){ this.merchantId=m; }
}
