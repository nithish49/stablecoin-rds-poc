package com.example.stablecoin.controller;
import com.example.stablecoin.service.VdpsIntegrationService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/vdps")
public class VdpsController {
    private final VdpsIntegrationService service;
    public VdpsController(VdpsIntegrationService service){ this.service = service; }
    @PostMapping("/ingest")
    public String ingest(@RequestBody String raw){
        service.accept(raw);
        return "APPROVED";
    }
}
