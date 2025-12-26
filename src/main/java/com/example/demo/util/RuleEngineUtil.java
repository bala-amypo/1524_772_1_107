public class RuleEngineUtil {

    public static double computeScore(String description, List<ClaimRule> rules) {
        double score = 0.0;
        for (ClaimRule rule : rules) {
            if ("always".equalsIgnoreCase(rule.getConditionExpression())) {
                score += rule.getWeight();
            } else if (rule.getConditionExpression().startsWith("description_contains:")) {
                String key = rule.getConditionExpression().split(":")[1];
                if (description.toLowerCase().contains(key.toLowerCase())) {
                    score += rule.getWeight();
                }
            }
        }
        return Math.min(score, 1.0);
    }
}
