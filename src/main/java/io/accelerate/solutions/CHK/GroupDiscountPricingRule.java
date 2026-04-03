package io.accelerate.solutions.CHK;

import java.util.ArrayList;
import java.util.Comparator;
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
        List<Integer> prices = extractPrices(itemCounts);
        prices.sort(Comparator.reverseOrder());
        int total = calculateGroupedTotal(prices);
        consumeItems(itemCounts);
        return total;
    }

    private List<Integer> extractPrices(Map<String, Integer> itemCounts) {
        var prices = new ArrayList<Integer>();
        for (String sku : skus) {
            int count = itemCounts.getOrDefault(sku, 0);

            if (count == 0) {
                continue;
            }
            Integer price = unitPrices.get(sku);

            if (price == null) {
                throw new NullPointerException("Missing unit price for SKU: " + sku);
            }

            for (int i = 0; i < count; i++) {
                prices.add(price);
            }

        }
        return prices;
    }

    private int calculateGroupedTotal(List<Integer> prices) {

        int total = 0;
        while (prices.size() >= groupSize) {
            for (int i = 0; i < groupSize; i++) {
                prices.remove(0);
            }
            total += groupPrice;
        }

        for (int price : prices) {
            total += price;
        }
        return total;
    }

    private void consumeItems(Map<String, Integer> itemCounts) {
        for (String sku : skus) {
            itemCounts.put(sku, 0);
        }
    }


}


