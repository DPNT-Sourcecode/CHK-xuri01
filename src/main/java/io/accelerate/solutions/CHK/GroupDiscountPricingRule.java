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
        validateInputs(itemCounts);
        var expandedItems = expandItems(itemCounts);
        sortByPriceDesc(expandedItems);
        return applyGroups(expandedItems, itemCounts);
    }

    private void validateInputs(Map<String, Integer> itemCounts) {
        if (itemCounts == null) {
            throw new NullPointerException("itemCounts cannot be null");
        }
    }

    private void sortByPriceDesc(List<String> expanded) {
        expanded.sort((a, b) -> unitPrices.get(b) - unitPrices.get(a));
    }

    private int applyGroups(List<String> expanded, Map<String, Integer> itemCounts) {

        int total = 0;
        int index = 0;

        while (index + groupSize <= expanded.size()) {

            for (int i = 0; i < groupSize; i++) {
                String sku = expanded.get(index + i);
                itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) - 1);
            }

            total += groupPrice;
            index += groupSize;
        }

        return total;
    }

private List<String> expandItems(Map<String, Integer> itemCounts) {
    List<String> expanded = new ArrayList<>();

    for (String sku : skus) {
        int count = itemCounts.getOrDefault(sku, 0);

        if (count == 0) continue;

        Integer price = unitPrices.get(sku);
        if (price == null) {
            throw new NullPointerException("Missing unit price for SKU: " + sku);
        }

        for (int i = 0; i < count; i++) {
            expanded.add(sku);
        }
    }

    return expanded;
}

}
















