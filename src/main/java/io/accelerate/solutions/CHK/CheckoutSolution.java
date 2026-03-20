package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    int total = 0;

    private static final Map<String, Integer> unitPrices = new HashMap<>() {{
        put("A", 50);
        put("B", 30);
        put("C", 20);
        put("D", 15);
    }};

    private static final Map<String, int[]> offers = new HashMap<>() {{
        put("A", new int[]{3, 130});
        put("B", new int[]{2, 45});
    }};


    public Integer checkout(String items) {

        if (items == null || items.isEmpty()) return 0;

        Map<String, Integer> counts = new HashMap<>();

        for (char c : items.toCharArray()) {
            String item = String.valueOf(c);
            if (!unitPrices.containsKey(item)) {
                counts.put(item, counts.getOrDefault(item, 0) + 1);
            }
        }

        int total = 0;

        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String sku = entry.getKey();
            int quantity = entry.getValue();
            int price = unitPrices.get(sku);

            if (offers.containsKey(sku)) {
                int[] offer = offers.get(sku);
                int offerQuantity = offer[0];
                int offerPrice = offer[1];

                total += (quantity / offerQuantity) * offerPrice;
                total += (quantity % offerQuantity) * price;
            } else {
                total += quantity * price;
            }
        }

        return total;

    }
}

