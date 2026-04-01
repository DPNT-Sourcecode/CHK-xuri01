package io.accelerate.solutions.CHK;

import java.util.List;
import java.util.Map;

public class GroupDiscountPricingRule implements PricingRule {

    private final List<String> skus;
    private Map<String, Integer> unitPrices;
    private final int groupSize;
    private final int groupSize;
    
    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        return 0;
    }
}

