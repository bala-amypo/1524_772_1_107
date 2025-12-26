package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.DamageClaim;
import com.example.demo.service.DamageClaimService;

@RestController
@RequestMapping("/claims")
public class DamageClaimController {

    private final DamageClaimService service;

    public DamageClaimController(DamageClaimService service) {
        this.service = service;
    }

    @PostMapping("/file/{parcelId}")
    public DamageClaim file(@PathVariable Long parcelId,
                            @RequestBody DamageClaim claim) {
        return service.fileClaim(parcelId, claim);
    }

    @PutMapping("/evaluate/{claimId}")
    public DamageClaim evaluate(@PathVariable Long claimId) {
        return service.evaluateClaim(claimId);
    }

    @GetMapping("/{claimId}")
    public DamageClaim get(@PathVariable Long claimId) {
        return service.getClaim(claimId);
    }
}
