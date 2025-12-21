package com.example.demo.service;

import com.example.demo.model.ClaimRule;
import com.example.demo.repository.ClaimRuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimRuleServiceImpl implements ClaimRuleService {

    private final ClaimRuleRepository ruleRepository;

    public ClaimRuleServiceImpl(ClaimRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public ClaimRule addRule(ClaimRule rule) {

        if (rule.getWeight() == null || rule.getWeight() <= 0) {
            throw new RuntimeException("Rule weight must be greater than 0");
        }

        return ruleRepository.save(rule);
    }

    @Override
    public List<ClaimRule> getAllRules() {
        return ruleRepository.findAll();
    }
}
