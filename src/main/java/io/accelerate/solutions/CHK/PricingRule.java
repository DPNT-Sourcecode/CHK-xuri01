package io.accelerate.solutions.CHK;

import java.util.Map;

public interface PricingRule {
    int calculate(Map<String, Integer> itemCounts);
}
