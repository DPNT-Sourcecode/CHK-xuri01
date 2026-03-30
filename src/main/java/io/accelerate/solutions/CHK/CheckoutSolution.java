package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private static final Map<String, Integer> PRICES = Map.of(
            "A", 50,
            "B", 30,
            "C", 20,
            "D", 15
    );


    public Integer checkout(String items) {

        if (items == null || items.isEmpty()) return 0;

        Map<String, Integer> itemCounts = new HashMap<>();

        for (char c : items.toCharArray()) {
            String sku = String.valueOf(c);

            if (!PRICES.containsKey(sku)) {
                return -1;
            }

            itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
        }

        applyFreeBPromotion(itemCounts);
        int total = 0;
        total += calculatePriceForA(itemCounts);
        total += calculatePriceForB(itemCounts);

        total += itemCounts.getOrDefault("C", 0) * PRICES.get("C");
        total += itemCounts.getOrDefault("D", 0) * PRICES.get("D");
        total += itemCounts.getOrDefault("E", 0) * PRICES.get("E");

        return total;
    }

    private void applyFreeBPromotion(Map<String, Integer> itemCounts) {
        int numberOfItems = itemCounts.getOrDefault("E", 0);
        int eligibleFreeBItems = numberOfItems / 2;
        int currentBItemCount = itemCounts.getOrDefault("B", 0);
        int chargeableBItems = Math.max(0, currentBItemCount - eligibleFreeBItems);
        itemCounts.put("B", chargeableBItems);

    }

    private int calculatePriceForA(Map<String, Integer> itemCounts) {
        int total = 0;
        int numberOfAItems = itemCounts.getOrDefault("A", 0);
        total += (numberOfAItems / 5) * 200;
        numberOfAItems %= 5;
        total += (numberOfAItems / 3) * 130;
        numberOfAItems %= 3;
        total += numberOfAItems * PRICES.get("A");
        return total;
    }

    private int calculatePriceForB(Map<String, Integer> itemCounts) {
        int total = 0;
        int numberOfBItems = itemCounts.getOrDefault("B", 0);
        total += (numberOfBItems / 2) * 45;
        total += (numberOfBItems % 2) * PRICES.get("B");
        return total;
    }
}