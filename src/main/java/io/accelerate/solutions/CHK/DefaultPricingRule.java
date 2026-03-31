package io.accelerate.solutions.CHK;

import java.util.Map;

public class DefaultPricingRule implements PricingRule{
    private final String sku;
    
    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        return 0;
    }
}
