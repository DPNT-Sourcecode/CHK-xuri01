package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutSolution {

    private final List<Promotion> promotions = List.of(new FreeBWithEPromotion());

    private final List<PricingRule> pricingRules = List.of(
            new APricingRule(),
            new BPricingRule(),
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

        for (Promotion promotion : promotions) {
            promotion.apply(itemCounts);
        }

        int total = 0;
        for (PricingRule rule : pricingRules) {
            total += rule.calculate(itemCounts);
        }
        return total;
    }

    private boolean isValidSku(String sku) {
        return List.of("A", "B", "C", "D", "E","F").contains(sku);
    }
}
