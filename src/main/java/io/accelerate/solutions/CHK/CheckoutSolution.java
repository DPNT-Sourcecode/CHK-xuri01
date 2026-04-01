package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckoutSolution {

    private static final Set<String> VALID_SKUS = Set.of("A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");

    private final List<Promotion> promotions = List.of(
            new BuyXGetYFreePromotion("E", 2, "B"),
            new BuyXGetYFreePromotion("N", 3, "M"),
            new BuyXGetYFreePromotion("R", 3, "Q"));


    private final List<PricingRule> pricingRules = List.of(

            new MultiBuyPricingRule("A", 50, Map.of(5, 200, 3, 130)),
            new MultiBuyPricingRule("B", 30, Map.of(2, 45)),
            new MultiBuyPricingRule("H", 10, Map.of(10, 80, 5, 45)),
            new MultiBuyPricingRule("K", 80, Map.of(2, 150)),
            new MultiBuyPricingRule("P", 50, Map.of(5, 200)),
            new MultiBuyPricingRule("Q", 30, Map.of(3, 80)),
            new MultiBuyPricingRule("V", 50, Map.of(3, 130, 2, 90)),
            new BuyNGetOneFreeSameSkuPricingRule("F", 10, 3),
            new BuyNGetOneFreeSameSkuPricingRule("U", 40, 4),
            new GroupDiscountPricingRule(List.of("S", "T", "X", "Y", "Z"),
                    3,
                    45,
                    Map.of(
                            "S", 30,
                            "T", 20,
                            "X", 90,
                            "Y", 10,
                            "Z", 50)
            ),
            new DefaultPricingRule("C", 20),
            new DefaultPricingRule("D", 15),
            new DefaultPricingRule("E", 40),
            new DefaultPricingRule("G", 20),
            new DefaultPricingRule("I", 35),
            new DefaultPricingRule("J", 60),
            new DefaultPricingRule("L", 90),
            new DefaultPricingRule("M", 15),
            new DefaultPricingRule("O", 10),
            new DefaultPricingRule("W", 20)

    );

    public Integer checkout(String items) {

        if (items == null || items.isEmpty()) return 0;

        var itemCounts = buildItemCounts(items);
        if (itemCounts == null) return -1;
        applyPromotions(itemCounts);

        /*for (char c : items.toCharArray()) {
            var sku = String.valueOf(c);

            if (!VALID_SKUS.contains(sku)) {
                return -1;
            }

            itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
        }*/

       /* for (Promotion promotion : promotions) {
            promotion.apply(itemCounts);
        }*/

        /*int total = 0;
        for (PricingRule rule : pricingRules) {
            total += rule.calculate(itemCounts);
        }
        return total;*/
        return calculateTotal(itemCounts);
    }

    private Map<String, Integer> buildItemCounts(String items) {
        var itemCounts = new HashMap<String, Integer>();

        for (char c : items.toCharArray()) {
            var sku = String.valueOf(c);

            if (!VALID_SKUS.contains(sku)) {
                return null;
            }

            itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
        }

        return itemCounts;
    }

    private void applyPromotions(Map<String, Integer> itemCounts) {
        for (Promotion promotion : promotions) {
            promotion.apply(itemCounts);
        }
    }

    private int calculateTotal(Map<String, Integer> itemCounts){
        int total = 0;
        for(PricingRule rule : pricingRules){
            total += rule.calculate(itemCounts);
        }
        return total;
    }
}