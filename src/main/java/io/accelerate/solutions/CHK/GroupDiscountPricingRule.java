package io.accelerate.solutions.CHK;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupDiscountPricingRule implements PricingRule {

    private final List<String> skus;
    private final int groupSize;
    private final int groupPrice;
    private final Map<String, Integer> unitPrices;

    public GroupDiscountPricingRule(List<String> skus, int groupSize, int groupPrice, Map<String, Integer> unitPrices) {
        this.skus = skus;
        this.groupSize = groupSize;
        this.groupPrice = groupPrice;
        this.unitPrices = unitPrices;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        List<Integer> prices = new ArrayList<>();
        for (String sku : skus) {
            int count = itemCounts.getOrDefault(sku, 0);
            for (int i = 0; i < count; i++) {
                prices.add(unitPrices.get(sku));
            }
            itemCounts.put(sku, 0);
            
        }
        return 0;
    }
}

