package io.accelerate.solutions.CHK;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupDiscountPricingRule implements PricingRule {

    private final List<String> skus;
    private final Map<String, Integer> unitPrices;
    private final int groupSize;
    private final int groupPrice;

    public GroupDiscountPricingRule(List<String> skus, Map<String, Integer> unitPrices, int groupSize, int groupPrice) {
        this.skus = skus;
        this.unitPrices = unitPrices;
        this.groupSize = groupSize;
        this.groupPrice = groupPrice;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        if (itemCounts == null) {
            throw new NullPointerException("itemCounts cannot be null");
        }

        List<Integer> expanded   = new ArrayList<>();

        // Expand all eligible items into price list
        for (String sku : skus) {
            int count = itemCounts.getOrDefault(sku, 0);
            Integer price = unitPrices.get(sku);

            if(count == 0) continue;

            if (price == null){
                throw new NullPointerException("Missing unit price for SKU:  "+ sku);
            };

            for (int i = 0; i < count; i++) {
                prices.add(price);
            }
        }

        // Sort DESC (CRITICAL)
        prices.sort((a, b) -> b - a);

        int total = 0;

        // Apply group discount
        while (prices.size() >= groupSize) {
            for (int i = 0; i < groupSize; i++) {
                prices.remove(0);
            }
            total += groupPrice;
        }

        // Add remaining items (FULL PRICE)
        for (int price : prices) {
            total += price;
        }

        // IMPORTANT: consume ALL these SKUs
        for (String sku : skus) {
            itemCounts.put(sku, 0);
        }

        return total;
    }

}

