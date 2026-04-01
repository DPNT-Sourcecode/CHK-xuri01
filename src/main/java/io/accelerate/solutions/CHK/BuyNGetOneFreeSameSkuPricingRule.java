package io.accelerate.solutions.CHK;

import java.util.Map;

public class BuyNGetOneFreeSameSkuPricingRule implements PricingRule {
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

        int groupTotal = groupSize;
        int count = itemCounts.getOrDefault(sku, 0);
        int freeItems = count / groupTotal;
        int payableItems = count - freeItems;
        itemCounts.put(sku, 0);

        return payableItems * unitPrice;


        /*int freeItems = count / groupSize;
        int payableItems = count - freeItems;
        return payableItems * unitPrice;*/
    }
}
