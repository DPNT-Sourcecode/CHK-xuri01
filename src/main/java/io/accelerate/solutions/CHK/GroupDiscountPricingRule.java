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
        List<String> items = extractItems(itemCounts);

        items.sort((a, b) -> unitPrices.get(b) - unitPrices.get(a));

        int total = 0;

        int groups = items.size() / groupSize;
        int itemsUsed = groups * groupSize;

      //apply group discount
        total += groups * groupPrice;

        // Charge remaining items normally
        for (int i = 0; i < itemsUsed; i++) {
            String sku = items.get(i);
            itemCounts.put(sku, itemCounts.get(sku) - 1);
        }

        return total;
    }

    private List<String> extractItems(Map<String, Integer> itemCounts) {

        var items = new ArrayList<String>();

        for (String sku : skus) {
            int count = itemCounts.getOrDefault(sku, 0);

            for (int i = 0; i < count; i++) {
                items.add(sku);
            }
        }

        return items;
    }

}

