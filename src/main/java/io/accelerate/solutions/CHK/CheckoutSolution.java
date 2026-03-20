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

        int total = 0;

        for(char item : items.toCharArray()){
            String key = String.valueOf(item);
            if(unitPrices.containsKey(key)){
                total += unitPrices.get(key);
            }else{
                return -1;
            }
        }

        return total;

    }
}


