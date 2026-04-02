package io.accelerate.solutions.CHK;

import java.util.List;
import java.util.Map;

public class MultiBuyPricingRule implements PricingRule {

    private final String sku;
    private final int unitPrice;
    private final Map<Integer, Integer> offers;
    private final List<Map.Entry<Integer, Integer>> sortedOffers;

    public MultiBuyPricingRule(String sku, int unitPrice, Map<Integer, Integer> offers) {
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.offers = offers;
        this.sortedOffers = offers.entrySet().stream().sorted((a, b) -> b.getKey() - a.getKey()).toList();
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        int count = itemCounts.getOrDefault(sku, 0);
        int total = 0;

        /*var sortedQuantities = offers.keySet().stream().sorted((a, b) -> b - a).toList();

        for (int quantity : sortedQuantities) {
            int offerPrice = offers.get(quantity);
            int times = count / quantity;
            total += times * offerPrice;
            count %= quantity;
        }

        total += count * unitPrice;

         */

        for (var entry : sortedOffers) {
            int quantity = entry.getKey();
            int offerPrice = entry.getValue();
            int times = count / quantity;
            total += times * offerPrice;
            count %= quantity;
        }
        total += count * unitPrice;

        itemCounts.put(sku, 0);
        return total;
    }
}


