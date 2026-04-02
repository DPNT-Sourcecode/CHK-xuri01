package io.accelerate.solutions.CHK;

import java.util.Map;
import java.util.Objects;

public class BuyNGetOneFreeSameSkuPricingRule implements PricingRule {
    private final String sku;
    private final int unitPrice;
    private final int groupSize;

    public BuyNGetOneFreeSameSkuPricingRule(String sku, int unitPrice, int groupSize) {
        this.sku = Objects.requireNonNull(sku, );
        this.unitPrice = unitPrice;
        this.groupSize = groupSize;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {

        int count = itemCounts.getOrDefault(sku, 0);
        int freeItems = count / groupSize;
        itemCounts.put(sku, 0);

        return payableItems * unitPrice;
    }
}


