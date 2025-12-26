package com.example.demo.service.impl;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import com.example.demo.service.DamageClaimService;
import org.springframework.stereotype.Service;

@Service
@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final DamageClaimRepository damageClaimRepository;
    private final ParcelRepository parcelRepository;
    private final ClaimRuleRepository claimRuleRepository;

    public DamageClaimServiceImpl(
            DamageClaimRepository damageClaimRepository,
            ParcelRepository parcelRepository,
            ClaimRuleRepository claimRuleRepository) {

        this.damageClaimRepository = damageClaimRepository;
        this.parcelRepository = parcelRepository;
        this.claimRuleRepository = claimRuleRepository;
    }


    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {
        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));

        claim.setParcel(parcel);
        claim.setStatus("FILED");

        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {
        DamageClaim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        claim.setStatus("APPROVED");

        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}

