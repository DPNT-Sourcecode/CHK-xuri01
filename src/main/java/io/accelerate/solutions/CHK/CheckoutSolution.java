package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckoutSolution {

    private static final Set<String> VALID_SKUS = Set.of("A", "B", "C", "D", "E", "F", "G",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    private final List<Promotion> promotions = List.of(
            new BuyXGetYFreePromotion("E", 2, "B"),
            new BuyXGetYFreePromotion("N", 3, "M"),
            new BuyXGetYFreePromotion("R", 3, "Q"));


    private final List<PricingRule> pricingRules = List.of(

            new MultiBuyPricingRule("A", 50, Map.of(5, 200, 3, 130)),[
            new MultiBuyPricingRule("B", 30, Map.of(2, 55)),
            new MultiBuyPricingRule("H", 30, Map.of(2, 55)),
            new MultiBuyPricingRule("K", 80, Map.of(2, )),


            ,[


                    ]
            new MultiBuyPricingRule("B", 30, Map.of(2, 45)),
            new BuyNGetOneFreeSameSkuPricingRule("F", 10, 3),
            new DefaultPricingRule("C", 20),
            new DefaultPricingRule("D", 15),
            new DefaultPricingRule("E", 40)
    );

    public Integer checkout(String items) {

        if (items == null || items.isEmpty()) return 0;

        Map<String, Integer> itemCounts = new HashMap<>();

        for (char c : items.toCharArray()) {
            String sku = String.valueOf(c);

            if (!VALID_SKUS.contains(sku)) {
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
}


