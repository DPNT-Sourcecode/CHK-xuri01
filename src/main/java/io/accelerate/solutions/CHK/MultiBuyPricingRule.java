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

        for (Map.Entry<Integer, Integer> offer : sortedOffers) {
            int quantity = offer.getKey();
            int price = offer.getValue();
            int times = count / quantity;

            if (times > 0) {
                total += times * price;
                count %= quantity;
            }
            total += times * price;
            count %= quantity;

            //only charge leftovers if no offer applied
            if (total == 0) {
                total += count * unitPrice;
            }
        }
        itemCounts.put(sku, 0);
        return total;
    }

}


