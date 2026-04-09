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
        List<String> items = expandGroupItems(itemCounts);
        sortByPriceDesc(items);

        int total = applyGroupDiscounts(items, itemCounts);
        total += priceRemainingItems(itemCounts);

        return total;
    }

    private List<String> expandGroupItems(Map<String, Integer> itemCounts) {
        List<String> items = new ArrayList<>();

        for (String sku : skus) {
            int count = itemCounts.getOrDefault(sku, 0);
            for (int i = 0; i < count; i++) {
                items.add(sku);
            }
        }

        return items;
    }

    private void sortByPriceDesc(List<String> items) {
        items.sort((a, b) -> unitPrices.get(b) - unitPrices.get(a));
    }

    private int applyGroupDiscounts(List<String> items, Map<String, Integer> itemCounts) {
        int total = 0;
        int index = 0;

        while (index + groupSize <= items.size()) {
            consumeGroup(items, index, itemCounts);
            total += groupPrice;
            index += groupSize;
        }

        return total;
    }

    private void consumeGroup(List<String> items, int startIndex, Map<String, Integer> itemCounts) {
        for (int i = 0; i < groupSize; i++) {
            String sku = items.get(startIndex + i);
            itemCounts.put(sku, itemCounts.get(sku) - 1);
        }
    }

    private int priceRemainingItems(Map<String, Integer> itemCounts) {
        int total = 0;

        for (String sku : skus) {
            int remaining = itemCounts.getOrDefault(sku, 0);

            if (remaining > 0) {
                total += remaining * unitPrices.get(sku);
                itemCounts.put(sku, 0); // consume
            }
        }

        return total;
    }
}













