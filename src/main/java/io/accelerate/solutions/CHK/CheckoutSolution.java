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

        int numberOfEItems = itemCounts.getOrDefault("E", 0);
        int freeBItemsFromPromotion = numberOfEItems / 2;

        int numberOfBItems = itemCounts.getOrDefault("B", 0);
        itemCounts.put("B", Math.max(0, numberOfBItems - freeBItemsFromPromotion));

        int total = calculateTotalWithSpecialOffers(itemCounts);
        total += itemCounts.getOrDefault("C", 0) * 20;
        total += itemCounts.getOrDefault("D", 0) * 15;

        return total;
    }

    private void applyFreeBPromotion(Map<String, Integer> itemCounts){
        int numberOfItems = itemCounts.getOrDefault("E", 0);
        int eligibleFreeBItems = numberOfItems
    }

    private int calculateTotalWithSpecialOffers(Map<String, Integer> count) {
        int total = 0;
        int numberOfAItems = count.getOrDefault("A", 0);
        total += (numberOfAItems / 3) * 130 + (numberOfAItems % 3) * 50;

        int numberOfBItems = count.getOrDefault("B", 0);
        total += (numberOfBItems / 2) * 45 + (numberOfBItems % 2) * 30;
        return total;

    }

}

