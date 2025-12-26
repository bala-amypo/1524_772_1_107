package com.example.demo.repository;
import java.util.List;

import com.example.demo.model.DamageClaim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DamageClaimRepository extends JpaRepository<DamageClaim, Long> {
    List<DamageClaim> findByParcel_Id(Long parcelId);
}
