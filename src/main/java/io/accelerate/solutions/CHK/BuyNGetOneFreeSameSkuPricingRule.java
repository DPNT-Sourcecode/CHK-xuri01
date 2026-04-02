package io.accelerate.solutions.CHK;

import java.util.Map;
import java.util.Objects;

public class BuyNGetOneFreeSameSkuPricingRule implements PricingRule {
    private final String sku;
    private final int unitPrice;
    private final int groupSize;

    public BuyNGetOneFreeSameSkuPricingRule(String sku, int unitPrice, int groupSize) {
        this.sku = Objects.requireNonNull(sku, "SKU cannot be null");
        if (groupSize <= 1) {
            throw new IllegalArgumentException("GroupSize must be greater than 1");
        }
        this.unitPrice = unitPrice;
        this.groupSize = groupSize;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        if (itemCounts == null) {
            throw new NullPointerException("item counts cannot be null");
        }
        int count = itemCounts.getOrDefault(sku, 0);
        if (count == 0) {
            return 0;
        }
        int freeItems = count / groupSize;
        int payableItems = count - freeItems;
        itemCounts.put(sku, 0);

        return payableItems * unitPrice;
    }
}





