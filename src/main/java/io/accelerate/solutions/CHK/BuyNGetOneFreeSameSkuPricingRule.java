package io.accelerate.solutions.CHK;

import java.util.Map;

public class BuyNGetOneFreeSameSkuPricingRule implements PricingRule{
    private final String sku;
    private final int unitPrice;
    private final int groupSize;

    public BuyNGetOneFreeSameSkuPricingRule(String sku, int unitPrice, int groupSize) {
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.groupSize = groupSize;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        int count = itemCounts.getOrDefault(sku)
        return 0;
    }
}