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

        int total = 0;
        if (items == null || items.isEmpty()) return -1;
        Map<String, Integer> countMap = new HashMap<>();

        for (char c : items.toCharArray()) {
            String item = String.valueOf(c);
            if (!unitPrices.containsKey(item)) {
                countMap.put(item, countMap.getOrDefault(item, 0) + 1);
            }
        }


        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();

            if (offers.containsKey(item)) {
                int[] offer = offers.get(item);
                int offerQuantity = offer[0];
                int offerPrice = offer[1];

                total += (quantity / offerQuantity) * offerPrice;
                total += (quantity % offerQuantity) * unitPrices.get(item);
            } else {
                total += quantity * unitPrices.get(item);
            }
        }

        return total;

    }
}




