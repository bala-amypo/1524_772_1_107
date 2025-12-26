package com.example.demo.service;

import com.example.demo.model.DamageClaim;
import com.example.demo.model.Evidence;
import com.example.demo.repository.DamageClaimRepository;
import com.example.demo.repository.EvidenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvidenceServiceImpl implements EvidenceService {

    private final EvidenceRepository evidenceRepository;
    private final DamageClaimRepository claimRepository;

    public EvidenceServiceImpl(EvidenceRepository evidenceRepository,
                               DamageClaimRepository claimRepository) {
        this.evidenceRepository = evidenceRepository;
        this.claimRepository = claimRepository;
    }

    @Override
    public Evidence uploadEvidence(Long claimId, Evidence evidence) {

        DamageClaim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        evidence.setClaim(claim);
        return evidenceRepository.save(evidence);
    }

    @Override
    public List<Evidence> getEvidenceForClaim(Long claimId) {

        if (!claimRepository.existsById(claimId)) {
            throw new RuntimeException("Claim not found");
        }

        return evidenceRepository.findByClaimId(claimId);
    }
}
