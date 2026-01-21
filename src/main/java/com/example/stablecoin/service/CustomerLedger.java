package com.example.stablecoin.service;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class CustomerLedger {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String customerAccountId;
    public LocalDate transactionDate;
    public String completionCode;
    public String transactionType;
    public BigDecimal amount;
    public String description;
    public String linkedDcNumber;
    public String networkId;
    public LocalDateTime createdAt = LocalDateTime.now();
}
