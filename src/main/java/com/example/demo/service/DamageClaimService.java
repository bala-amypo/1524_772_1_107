package com.example.demo.service;

import com.example.demo.model.DamageClaim;

import java.util.List;

public interface DamageClaimService {

    DamageClaim createClaim(DamageClaim claim);

    List<DamageClaim> getAllClaims();

    DamageClaim getClaimById(Long id);
}
