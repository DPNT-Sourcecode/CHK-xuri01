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
        Map<String, Integer> count = new HashMap<>();

        for (char c : items.toCharArray()) {
            count.put(String.valueOf(c), count.getOrDefault(c, 0) + 1);
        }
        int total = 0;

        if (count.containsKey('A')) {
            int aCount = count.get('A');
            total += (aCount / 3) * 130;
            total += (aCount % 3) * 50;
        }

        if (count.containsKey('B')) {
            int bCount = count.get('B');
            total += (bCount / 2) * 45;
            total += (bCount % 2) * 30;

        }

        total += count.getOrDefault('C',0) + 20;
        total+=count.getOrDefault(0, )


        /////
        for (char item : items.toCharArray()) {
            String key = String.valueOf(item);
            if (unitPrices.containsKey(key)) {
                total += unitPrices.get(key);
            } else {
                return -1;
            }
        }

        return total;

    }
}







