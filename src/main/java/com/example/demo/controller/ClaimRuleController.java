package com.example.demo.controller;

import com.example.demo.model.ClaimRule;
import com.example.demo.service.ClaimRuleService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
@Tag(name = "Claim Rules")
public class ClaimRuleController {

    private final ClaimRuleService ruleService;

    public ClaimRuleController(ClaimRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ClaimRule addRule(@RequestBody ClaimRule rule) {
        return ruleService.addRule(rule);
    }

    @GetMapping
    public List<ClaimRule> getAllRules() {
        return ruleService.getAllRules();
    }
}

