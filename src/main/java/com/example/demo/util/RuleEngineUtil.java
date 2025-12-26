package com.example.demo.util;

import com.example.demo.model.ClaimRule;
import java.util.List;

public class RuleEngineUtil {

    public static double computeScore(String description, List<ClaimRule> rules) {
        if (rules == null || rules.isEmpty()) {
            return 0.0;
        }

        double score = 0.0;

        for (ClaimRule rule : rules) {
            String condition = rule.getConditionExpression();

            if ("always".equalsIgnoreCase(condition)) {
                score += rule.getWeight();
            }
            else if (condition.startsWith("description_contains:")) {
                String keyword = condition.split(":")[1];
                if (description != null &&
                        description.toLowerCase().contains(keyword.toLowerCase())) {
                    score += rule.getWeight();
                }
            }
        }

        return Math.min(score, 1.0);
    }
}
