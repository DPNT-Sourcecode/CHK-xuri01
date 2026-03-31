package io.accelerate.solutions.CHK;

import java.util.Map;

public class BPricingRule implements PricingRule{
    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        int count = itemCounts.getOrDefault("B", 0);
        return (count / 2) * 45 +( count%2) * 30;
    }
}
