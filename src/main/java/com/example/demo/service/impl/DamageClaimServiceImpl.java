package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.service.DamageClaimService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  
public class DamageClaimServiceImpl implements DamageClaimService {

    private final DamageClaimRepository damageClaimRepository;

    public DamageClaimServiceImpl(DamageClaimRepository damageClaimRepository) {
        this.damageClaimRepository = damageClaimRepository;
    }

    @Override
    public DamageClaim createClaim(DamageClaim claim) {
        return damageClaimRepository.save(claim);
    }

    @Override
    public List<DamageClaim> getAllClaims() {
        return damageClaimRepository.findAll();
    }

    @Override
    public DamageClaim getClaimById(Long id) {
        return damageClaimRepository.findById(id).orElse(null);
    }
}
