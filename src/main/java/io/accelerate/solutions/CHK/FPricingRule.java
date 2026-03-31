package io.accelerate.solutions.CHK;

import java.util.Map;

public class FPricingRule implements PricingRule {
    private static final String SKU = "F";
    private static final int UNIT_PRICE = 10;
    private static final int GROUP_SIZE = 3;

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        int count = itemCounts.getOrDefault(SKU, 0);
        int freeItems = count / GROUP_SIZE;
        int payableItems = count - freeItems;

        return payableItems * UNIT_PRICE;
    }
}
