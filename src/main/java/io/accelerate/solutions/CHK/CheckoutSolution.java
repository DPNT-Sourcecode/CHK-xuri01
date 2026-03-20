package io.accelerate.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {

    int total = 0;

    private static final Map<String, Integer> prices = new HashMap<>() {{
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
            String key = String.valueOf(c);

            if (prices.containsKey(key)) {
                return -1;
            }
            count.put(key,  count.getOrDefault(c, 0) + 1);
        }
        int total = 0;

        int countA = count.getOrDefault('A', 0);
        total += ((countA / 3) * 130) + ((countA % 3) * 30);

        int countB = count.getOrDefault('B', 0);
        total+=((countB/2) * 45) + ((countB % 2) * 30);

        total += count.getOrDefault('C',0) * 20;

        total+=count.getOrDefault('D',0) * 15;

        return total;

    }
}



