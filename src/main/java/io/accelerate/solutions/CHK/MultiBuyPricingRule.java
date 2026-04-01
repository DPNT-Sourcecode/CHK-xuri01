package io.accelerate.solutions.CHK;

import java.util.Comparator;
import java.util.Map;

public class MultiBuyPricingRule implements PricingRule{

    private final String sku;
    private final int unitPrice;
    private final Map<Integer, Integer> offers;

    public MultiBuyPricingRule(String sku, int unitPrice, Map<Integer, Integer> offers) {
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.offers = offers;
    }

    @Override
    public int calculate(Map<String, Integer> itemCounts) {
        int count = itemCounts.getOrDefault(sku, 0);
        int total = 0;

        var sortedQuantities = offers.keySet().stream().sorted((a,b) -> b - a).toList();

        for(int quantity : sortedQuantities){
            int offerPrice = offers.get(quantity);
            int times = count / quantity;
            total += times * offerPrice;
            count %=quantity;
        }

        total += count * unitPrice;

        var sortedOffers = offers.keySet().stream().sorted(Comparator.reverseOrder()).toList();


        for(int offerQty : sortedOffers){
            int offerPrice = offers.get(offerQty);
            total += (count / offerQty) * offerPrice;
            count += count * unitPrice;
            return total;
        }

        return 0;
    }
}



