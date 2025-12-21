package com.example.demo.service;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Parcel;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.ParcelRepository;
import org.springframework.stereotype.Service;

@Service
public class DamageClaimServiceImpl implements DamageClaimService {

    private final DamageClaimRepository claimRepository;
    private final ParcelRepository parcelRepository;

    public DamageClaimServiceImpl(DamageClaimRepository claimRepository,
                                  ParcelRepository parcelRepository) {
        this.claimRepository = claimRepository;
        this.parcelRepository = parcelRepository;
    }

    @Override
    public DamageClaim fileClaim(Long parcelId, DamageClaim claim) {

        Parcel parcel = parcelRepository.findById(parcelId)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));

        claim.setParcel(parcel);
        // status = PENDING set automatically
        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim evaluateClaim(Long claimId) {

        DamageClaim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        // -------- RULE EVALUATION (NO UTIL) --------
        double score = 0.0;

        if (claim.getClaimDescription() != null) {
            String desc = claim.getClaimDescription().toLowerCase();

            if (desc.contains("severe")) {
                score = 80.0;
            } else if (desc.contains("moderate")) {
                score = 60.0;
            } else {
                score = 40.0;
            }
        }

        claim.setScore(score);

        
        if (score >= 60) {
            claim.setStatus("APPROVED");
        } else {
            claim.setStatus("REJECTED");
        }

        return claimRepository.save(claim);
    }

    @Override
    public DamageClaim getClaim(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }
}