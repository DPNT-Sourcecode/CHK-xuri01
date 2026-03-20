package io.accelerate.solutions.CHK;

import io.accelerate.runner.SolutionNotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    private static final Map<String, Integer> unitPrices = new HashMap<>() {{
        put("A", 50),
                put("B", 30),
                put("C", 20),
                put("D", 15)
    }};

    private static final Map<String, int[]> offers = new HashMap<>() {{
        put("A", new int[]{3, 130});
        put("B", new int[]{2, 45});
    }};


    public Integer checkout(String items) {
        if (items == null || items.isEmpty()) return -1;
        Map<String, Integer> countMap = new HashMap<>();

        for (char c : items.toCharArray()) {
            String item = String.valueOf(c);
            if (!unitPrices.containsKey(item)) {
                countMap.put(item, countMap.getOrDefault(item, 0) + 1)
            }
        }

        int total = 0;

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();

            if(offers.containsKey(item)){
                int[] offer  = offers.get(item);
                int offerQuantity = offer[0];
                
            }

        }


        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        for (char sku : skus.toCharArray()) {
            switch (sku) {
                case 'A':
                    countA++;
                    break;
                case 'B':
                    countB++;
                    break;
                case 'C':
                    countC++;
                    break;
                case 'D':
                    countD++;
                    break;
                default:
                    return -1;
            }
        }

        int total = 0;
        total += (countA / 3) * 130 + (countA % 3) * 50;
        total += (countB / 2) * 45 + (countB % 2) * 30;
        total += countC * 20;
        total += countD * 15;
        return total;
    }
}





