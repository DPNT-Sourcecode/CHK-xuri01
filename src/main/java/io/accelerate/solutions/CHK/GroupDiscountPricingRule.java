package io.accelerate.solutions.CHK;

import java.util.List;
import java.util.Map;

public class GroupDiscountPricingRule implements PricingRule{

    private final List<String> skus;
    private final int groupSize;
    private final int groupPrice;
    private final Map<String, Integer>
    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        return 0;
    }
}
