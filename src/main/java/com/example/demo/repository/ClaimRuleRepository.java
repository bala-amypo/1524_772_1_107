package com.example.demo.repository;
import java.util.List;

import com.example.demo.model.ClaimRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRuleRepository extends JpaRepository<ClaimRule, Long> {
    List<ClaimRule> findAll();
}
