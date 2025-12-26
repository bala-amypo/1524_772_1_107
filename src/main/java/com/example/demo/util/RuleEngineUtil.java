package com.example.demo.util; 
 
import com.example.demo.model.ClaimRule; 
 
import java.util.List; 
 
public class RuleEngineUtil { 
 
   
    public static double computeScore(String description, List<ClaimRule> rules) { 
        if (description == null || rules == null || rules.isEmpty()) { 
            return 0.0; 
        } 
 
        double totalWeight = rules.stream().mapToDouble(r -> r.getWeight() != null ? r.getWeight() : 0.0).sum(); 
 
        if (totalWeight <= 0) return 0.0; 
 
        double matchedWeight = 0.0; 
 
        for (ClaimRule rule : rules) { 
            String expr = rule.getExpression(); 
            double weight = rule.getWeight() != null ? rule.getWeight() : 0.0; 
 
            try { 
                if ("always".equalsIgnoreCase(expr)) { 
                    matchedWeight += weight; 
                } else if (expr.startsWith("description_contains:")) { 
                    String keyword = expr.split(":", 2)[1].toLowerCase(); 
                    if (description.toLowerCase().contains(keyword)) { 
                        matchedWeight += weight; 
                    } 
                } 
            } catch (Exception e) { 
                
            } 
        } 
 
        
        return Math.min(1.0, matchedWeight / totalWeight); 
}
}