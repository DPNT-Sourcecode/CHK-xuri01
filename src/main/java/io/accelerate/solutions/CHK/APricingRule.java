package io.accelerate.solutions.CHK;

import java.util.Map;

public class APricingRule implements PricingRule{
    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        int count = itemCounts.getOrDefault("A", 0);
        int total = (count / 5) * 200;
        count %=5;
        total+=(count/3) * 130;
        count %=3;
        total +=count * 50;
        return total;
    }
}
