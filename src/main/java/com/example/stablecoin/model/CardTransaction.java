package com.example.stablecoin.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CardTransaction {
    private String customerAccountId;
    private LocalDate transactionDate;
    private String completionCode;
    private String transactionType;
    private BigDecimal amount;
    private String description;
    private String linkedDcNumber;
    private String networkId;

    public String getCustomerAccountId() { return customerAccountId; }
    public void setCustomerAccountId(String customerAccountId) { this.customerAccountId = customerAccountId; }
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
    public String getCompletionCode() { return completionCode; }
    public void setCompletionCode(String completionCode) { this.completionCode = completionCode; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLinkedDcNumber() { return linkedDcNumber; }
    public void setLinkedDcNumber(String linkedDcNumber) { this.linkedDcNumber = linkedDcNumber; }
    public String getNetworkId() { return networkId; }
    public void setNetworkId(String networkId) { this.networkId = networkId; }
}
