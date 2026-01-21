package com.example.stablecoin.service;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class SwitchSettlement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String switchAccountGl;
    public LocalDate effectiveDate;
    public String transactionType;
    public BigDecimal amount;
    public String description;
    public LocalDateTime createdAt = LocalDateTime.now();
}
