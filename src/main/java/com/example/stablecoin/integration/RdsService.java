package com.example.stablecoin.integration;
import com.example.stablecoin.model.CardTransaction;
import org.springframework.stereotype.Service;
@Service
public class RdsService {
    public boolean requiresStablecoin(CardTransaction tx){ return true; }
}
