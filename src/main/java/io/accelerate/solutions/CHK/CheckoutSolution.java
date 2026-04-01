package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    private final List<Promotion> promotions = List.of(
            new BuyXGetYFreePromotion("E", 2, "B"),
            new BuyXGetYFreePromotion("N", 3, "M"),
            new BuyXGetYFreePromotion("R", 3, "Q"));


    private final List<PricingRule> pricingRules = List.of(

            new MultiBuyPricingRule("A", 50, Map.of(5, 200, 3, 130)),
            new MultiBuyPricingRule("B", 30, Map.of(5, 200, 3, 130)),


            new APricingRule(),
            new BPricingRule(),
            new FPricingRule(),
            new DefaultPricingRule("C", 20),
            new DefaultPricingRule("D", 15),
            new DefaultPricingRule("E", 40)
    );

    public Integer checkout(String items) {

        if (items == null || items.isEmpty()) return 0;

        Map<String, Integer> itemCounts = new HashMap<>();

        for (char c : items.toCharArray()) {
            String sku = String.valueOf(c);

            if (!isValidSku(sku)) {
                return -1;
            }

            itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
        }

        int total = 0;
        for (Promotion promotion : promotions) {
           // promotion.apply(itemCounts);
            total = promotion.apply(itemCounts, total);
        }

        for (PricingRule rule : pricingRules) {
            total += rule.calculate(itemCounts);
        }
        return total;
    }

    private boolean isValidSku(String sku) {
        return List.of("A", "B", "C", "D", "E","F").contains(sku);
    }
}
