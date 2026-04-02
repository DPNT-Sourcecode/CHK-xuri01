package io.accelerate.solutions.CHK;

import java.util.Map;

public class DefaultPricingRule implements PricingRule {
    private final String sku;
    private final int price;

    public DefaultPricingRule(String sku, int price) {
        this.sku = sku;
        this.price = price;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        int count = itemCounts.getOrDefault(sku, 0);
        int total = count * price;

        //consume items
        itemCounts.put(sku, 0);
        //return itemCounts.getOrDefault(sku, 0) * price;
        return total;
    }
}
