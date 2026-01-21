package com.example.stablecoin.service;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerLedgerRepo extends JpaRepository<CustomerLedger, Long> {}
