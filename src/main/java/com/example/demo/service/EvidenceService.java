package com.example.demo.service; 
 
import com.example.demo.model.Evidence; 
import java.util.List; 
 
public interface EvidenceService { 
 
    Evidence createEvidence(Evidence evidence); 
 
    Evidence getById(Long id); 
 
    List<Evidence> getAllEvidence(); 
 
    //    REQUIRED BY TEST 
    Evidence uploadEvidence(Long claimId, Evidence evidence); 
 
    //    REQUIRED BY TEST 
    List<Evidence> getEvidenceForClaim(Long claimId); 
}
