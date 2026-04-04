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

        //Expand items into a list of SKUs
        List<String> expanded = new ArrayList<>();
        for (String sku : skus) {
            int count = itemCounts.getOrDefault(sku, 0);
            for (int i = 0; i < count; i++) {
                expanded.add(sku);
            }
        }

        //Sort by price desc (most expensive first)
        expanded.sort((a, b) -> unitPrices.get(b) - unitPrices.get(a));

        int total = 0;
        int index = 0;

        while (index + groupSize <= expanded.size()) {
            for (int i = 0; i < groupSize; i++) {
                String sku = expanded.get(index + i);
                itemCounts.put(sku, itemCounts.get(sku) - 1);
            }
            total += groupPrice;
            index += groupSize;

        }

        return total;

    }

}
